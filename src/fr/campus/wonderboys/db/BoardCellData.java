package fr.campus.wonderboys.db;

public class BoardCellData {
    private int position;
    private String cellType;
    private String contentName;

    public BoardCellData(int position, String cellType, String contentName) {
        this.position = position;
        this.cellType = cellType;
        this.contentName = contentName;
    }

    public int getPosition() {
        return position;
    }

    public String getCellType() {
        return cellType;
    }

    public String getContentName() {
        return contentName;
    }

    @Override
    public String toString() {
        return "BoardCellData{" +
                "position=" + position +
                ", cellType='" + cellType + '\'' +
                ", contentName='" + contentName + '\'' +
                '}';
    }
}
