package hu.flowacademy.epsilon.sport_event_organizer.service;

import hu.flowacademy.epsilon.sport_event_organizer.model.Cup;
import hu.flowacademy.epsilon.sport_event_organizer.model.Match;
import hu.flowacademy.epsilon.sport_event_organizer.model.Team;
import hu.flowacademy.epsilon.sport_event_organizer.repository.MatchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class MatchService {

    @Autowired
    private CupService cupService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchRepository matchRepository;

    public List<Match> generateGroupMatches(String cupName) {
        Cup cup = cupService.getByName(cupName);
        List<Team> cupTeams = new ArrayList<Team>(cupService.getApprovedTeams(cupName));
        List<Match> matches = new ArrayList();
        if (cupTeams.size() == 8) {
            String groupName = "Group1";
            for (int i = 0; i < 2; i++) {
                for (int j = 1; j < 4; j++) {
                    Match match = new Match();
                    match.setTeamA(cupTeams.get(0));
                    match.setTeamB(cupTeams.get(j));
                    match.setGroupName(groupName);
                    matches.add(match);
                }
                for (int j = 2; j < 4; j++) {
                    Match match = new Match();
                    match.setTeamA(cupTeams.get(1));
                    match.setTeamB(cupTeams.get(j));
                    match.setGroupName(groupName);
                    matches.add(match);
                }
                Match match = new Match();
                match.setTeamA(cupTeams.get(2));
                match.setTeamB(cupTeams.get(3));
                match.setGroupName(groupName);
                matches.add(match);
                for (int j = 0; j < 3; j++) {
                    cupTeams.get(j).setGroupName(groupName);
                }
                cupTeams.remove(3);
                cupTeams.remove(2);
                cupTeams.remove(1);
                cupTeams.remove(0);
                groupName = "Group2";
            }
        }

        //Pálya kiosztás
        matches.forEach(match -> match.setCup(cup));
        String courtName = "Court1";
        for (int i = 0; i < matches.size(); i++) {
            matches.get(i).setCourtName(courtName);
            if ((cup.getCourtCounter() == 2 && courtName == "Court2") || (cup.getCourtCounter() == 3 && courtName == "Court3") || (cup.getCourtCounter() == 4 && courtName == "Court4")) {
                courtName = "Court1";
            } else if (cup.getCourtCounter() >= 2 && courtName == "Court1") {
                courtName = "Court2";
            } else if (cup.getCourtCounter() >= 3 && courtName == "Court2") {
                courtName = "Court3";
            } else if (cup.getCourtCounter() >= 4 && courtName == "Court3") {
                courtName = "Court4";
            }
        }

        matches.forEach(match -> matchRepository.save(match));


        return matches;
    }

    public List<Match> generatequalifierMatches(String cupName) {
        Cup cup = cupService.getByName(cupName);
        List<Team> cupTeams = new ArrayList<Team>(cupService.getApprovedTeams(cupName));
        ArrayList<Match> matches = new ArrayList<>(cup.getMatches());
        ArrayList<Team> winners = new ArrayList();
        ArrayList<Team> group1 = new ArrayList();
        ArrayList<Team> group2 = new ArrayList();

        cupTeams.forEach(team -> team.setWinnerCounter(0));
        cupTeams.forEach(team -> team.setQualified(false));
//győztesek kiszámítása
        for (Match match : matches) {
            if (match.getTeamAScore() > match.getTeamBScore()) {
                match.getTeamA().setWinnerCounter(match.getTeamA().getWinnerCounter() + 1);
            } else {
                match.getTeamB().setWinnerCounter(match.getTeamB().getWinnerCounter() + 1);
            }
            match.getTeamA().setGoalDifference(match.getTeamAScore() - match.getTeamBScore());
            match.getTeamB().setGoalDifference(match.getTeamBScore() - match.getTeamAScore());
        }

//ki hányszor nyert
        for (Team team : cupTeams) {
            if (team.getGroupName() == "Group1") {
                group1.add(team);
            } else {
                group2.add(team);
            }
        }

//újrajátszások kiszűrése vagy továbbjutottak
        ArrayList<ArrayList<Team>> groups = new ArrayList();
        groups.add(group1);
        groups.add(group2);
        for (ArrayList<Team> group : groups) {
            int oneTimeWin = 0;
            int twoTimesWin = 0;
            int threeTimesWin = 0;
            for (Team team : group) {
                if (team.getWinnerCounter() == 1) {
                    oneTimeWin++;
                } else if (team.getWinnerCounter() == 2) {
                    twoTimesWin++;
                } else if (team.getWinnerCounter() == 3) {
                    threeTimesWin++;
                }
            }

            if (threeTimesWin == 1 && oneTimeWin == 3) {
                Team qualificationTeam = null;
                for (Team team : group) {
                    if (team.getWinnerCounter() == 3) {
                        team.setQualified(true);
                    } else {

                        if (qualificationTeam == null) {
                            qualificationTeam = team;
                        } else if (team.getGoalDifference() > team.getGoalDifference()) {
                            qualificationTeam = team;
                        }
                    }
                }
                qualificationTeam.setQualified(true);
                return null;
            } else if (twoTimesWin == 3) {
                Team notQualificationTeam = null;
                for (Team team : group) {
                    if (team.getWinnerCounter() == 2) {
                        if (notQualificationTeam == null) {
                            notQualificationTeam = team;
                        } else if (notQualificationTeam.getGoalDifference() > team.getGoalDifference()) {
                            notQualificationTeam = team;
                        }
                    }
                }
                for (Team team : group) {
                    if (team.getWinnerCounter() == 2 && team != notQualificationTeam) {
                        team.setQualified(true);
                    }
                }

            } else {
                for (Team team : group) {
                    if (team.getWinnerCounter() == 3 && team.getWinnerCounter() == 2) {
                        team.setQualified(true);
                    }
                    teamService.save(team);
                }
            }
        }

        //meccsek legenerálása
        cupTeams = new ArrayList<Team>(cupService.getApprovedTeams(cupName));
        Match cNotQualifiedMatch = new Match();
        Match dNotQualifiedMatch = new Match();
        Match eQualifiedMatch = new Match();
        Match fQualifiedMatch = new Match();
        List<Match> qualifierMatches = new ArrayList();

        for (Team team : cupTeams) {
            if (team.isQualified()) {
                if (team.getGroupName() == "Group1") {
                    eQualifiedMatch.setTeamA(team);
                } else {
                    eQualifiedMatch.setTeamB(team);
                }
            } else {
                if (team.getGroupName() == "Group1") {
                    cNotQualifiedMatch.setTeamA(team);
                } else {
                    cNotQualifiedMatch.setTeamB(team);
                }
            }
//            cupTeams.remove(team);
        }
        for (Team team : cupTeams) {
            if (team.isQualified()) {
                if (team.getGroupName() == "Group1") {
                    fQualifiedMatch.setTeamA(team);
                } else {
                    fQualifiedMatch.setTeamB(team);
                }
            } else {
                if (team.getGroupName() == "Group1") {
                    dNotQualifiedMatch.setTeamA(team);
                } else {
                    dNotQualifiedMatch.setTeamB(team);
                }
            }
//            cupTeams.remove(team);
        }


        qualifierMatches.add(cNotQualifiedMatch);
        qualifierMatches.add(dNotQualifiedMatch);
        qualifierMatches.add(eQualifiedMatch);
        qualifierMatches.add(fQualifiedMatch);


        qualifierMatches.forEach(match -> match.setCup(cup));
        qualifierMatches.forEach(match -> match.setGroupName("Qualifier"));

        //pályák szétosztása
        String courtName = "Court1";
        for (int i = 0; i < matches.size(); i++) {
            matches.get(i).setCourtName(courtName);
            if ((cup.getCourtCounter() == 2 && courtName == "Court2") || (cup.getCourtCounter() == 3 && courtName == "Court3") || (cup.getCourtCounter() == 4 && courtName == "Court4")) {
                courtName = "Court1";
            } else if (cup.getCourtCounter() >= 2 && courtName == "Court1") {
                courtName = "Court2";
            } else if (cup.getCourtCounter() >= 3 && courtName == "Court2") {
                courtName = "Court3";
            } else if (cup.getCourtCounter() >= 4 && courtName == "Court3") {
                courtName = "Court4";
            }
        }
        qualifierMatches.forEach(match -> matchRepository.save(match));


        return qualifierMatches;
    }

    public Match postMatchScore(Long matchId, Integer aScore, Integer bScore) {
        Match match = matchRepository.findById(matchId).orElse(null);
        match.setTeamAScore(aScore);
        match.setTeamBScore(bScore);
        return matchRepository.save(match);
    }
}
