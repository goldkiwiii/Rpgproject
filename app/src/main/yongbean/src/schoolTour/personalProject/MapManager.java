package schoolTour.personalProject;

public class MapManager {

    public static String getNextMap(String currentMap){
        return switch(currentMap) {
            case "campus" -> "library"; // 캠퍼스에서 도서관으로 이동
            case "library" -> "dormitory"; // 도서관에서 기숙사로 이동
            default -> "campus"; // 기본: 캠퍼스
        };
    }
}
