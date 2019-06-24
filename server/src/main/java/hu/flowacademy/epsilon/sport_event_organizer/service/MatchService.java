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
        ArrayList<Team> cupTeams = (ArrayList) cup.getTeams();
        List<Match> matches = new ArrayList();
        if (cup.getTeams().size() == 8) {
            String groupName = "Group1";
            for (int i = 0; i < 2; i++) {
                for (int j = 1; j < 4; j++) {
                    Match match = new Match();
                    match.setTeamA(cupTeams.get(0));
                    match.setTeamB(cupTeams.get(j));
                    match.setGroupName(groupName);
                    matches.add(match);
                }
                for (int j = 3; j < 4; j++) {
                    Match match = new Match();
                    match.setTeamA(cupTeams.get(2));
                    match.setTeamB(cupTeams.get(j));
                    match.setGroupName(groupName);
                    matches.add(match);
                }
                Match match = new Match();
                match.setTeamA(cupTeams.get(3));
                match.setTeamB(cupTeams.get(4));
                match.setGroupName(groupName);
                matches.add(match);
                for (int j = 0; j < 3; j++) {
                    cupTeams.get(j).setGroupName(groupName);
                }
                cupTeams.subList(0, 3);
                groupName = "Group2";
            }
        }

        //Pálya kiosztás
        matches.forEach(match -> match.setCup(cup));
        matches.forEach(match -> match.setCourtName("Court1"));
        if (cup.getCourtCounter() >= 2) {
            for (int i = 0; i < matches.size(); i++) {
                if (i % 2 == 0) {
                    matches.get(i).setCourtName("Court2");
                }
                if (i % 3 == 0 && cup.getCourtCounter() >= 3) {
                    matches.get(i).setCourtName("Court3");
                }
                if (i % 4 == 0 && cup.getCourtCounter() >= 4) {
                    matches.get(i).setCourtName("Court4");
                }
            }
        }
        return matches;
    }

    public List<Match> generatequalifierMatches(String cupName) {
        Cup cup = cupService.getByName(cupName);
        ArrayList<Team> cupTeams = (ArrayList) cup.getTeams();
        ArrayList<Match> matches = (ArrayList) cup.getMatches();
        ArrayList<Team> winners = new ArrayList();
        ArrayList<Team> group1 = new ArrayList();
        ArrayList<Team> group2 = new ArrayList();

//győztesek kiszámítása
        for (Match match : matches) {
            if (match.getTeamAScore() > match.getTeamBScore()) {
                winners.add(match.getTeamA());
            } else {
                winners.add(match.getTeamB());
            }
        }

//ki hányszor nyert
        for (Team team : cupTeams) {
            int teamWinnerCounter = 0;
            for (Team winner : winners) {
                if (winner.getName().equals(team)) {
                    teamWinnerCounter++;
                }
            }
            team.setWinnerCounter(teamWinnerCounter);
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
            if (threeTimesWin == 1 && oneTimeWin == 3 || twoTimesWin == 3) {
                //újrajátszás
                return null;
            }
            for (Team team : group) {
                if (team.getWinnerCounter() == 3 && team.getWinnerCounter() == 2) {
                    team.setQualified(true);
                } else {
                    team.setQualified(false);
                }
                teamService.save(team);
            }
        }

        //meccsek legenerálása
        cupTeams = (ArrayList) cup.getTeams();
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
            cupTeams.remove(team);
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
            cupTeams.remove(team);
        }


        qualifierMatches.add(cNotQualifiedMatch);
        qualifierMatches.add(dNotQualifiedMatch);
        qualifierMatches.add(eQualifiedMatch);
        qualifierMatches.add(fQualifiedMatch);


        qualifierMatches.forEach(match -> match.setCup(cup));
        qualifierMatches.forEach(match -> match.setGroupName("Qualifier"));
        //pályák szétosztása
        qualifierMatches.forEach(match -> match.setCourtName("Court1"));

        if (cup.getCourtCounter() >= 2) {
            for (int i = 0; i < qualifierMatches.size(); i++) {
                if (i % 2 == 0) {
                    qualifierMatches.get(i).setCourtName("Court2");
                }
                if (i % 3 == 0 && cup.getCourtCounter() >= 3) {
                    qualifierMatches.get(i).setCourtName("Court3");
                }
                if (i % 4 == 0 && cup.getCourtCounter() >= 4) {
                    qualifierMatches.get(i).setCourtName("Court4");
                }
            }
        }


        return qualifierMatches;
    }

    public Match postMatchScore(Long matchId, Integer aScore, Integer bScore) {
        Match match = matchRepository.findById(matchId).orElse(null);
        match.setTeamAScore(aScore);
        match.setTeamBScore(bScore);
        return matchRepository.save(match);
    }
}
