package Avocado.main_project.Entities.team;

import Avocado.main_project.Entities.user.Users;

public class UserTeamRequest {
    private Users user;
    private Team team;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
