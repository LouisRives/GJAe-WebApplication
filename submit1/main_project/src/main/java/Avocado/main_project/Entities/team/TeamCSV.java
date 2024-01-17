package Avocado.main_project.Entities.team;

import java.util.List;

public class TeamCSV {
    private Long teamId;

    private String studentIds;

    private Long courseId;

    public String teamName;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(String studentIds) {
        this.studentIds = studentIds;
    }

    @Override
    public String toString() {
        return "TeamCSV{" +
                "teamId=" + teamId +
                ", studentIds='" + studentIds + '\'' +
                ", courseId=" + courseId +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
