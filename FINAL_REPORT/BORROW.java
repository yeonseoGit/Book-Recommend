package FINAL_REPORT;

public class BORROW {
    private String TODAY_DATE;
    private int BORROW_NUMBER;
    private int BOOK_NUMBER;
    private int STUDENT_ID;
    private String DUE_DATE;
    private String BOOK_NAME; // 추가된 필드

    // Getter and Setter methods
    public String getTODAY_DATE() {
        return TODAY_DATE;
    }

    public void setTODAY_DATE(String TODAY_DATE) {
        this.TODAY_DATE = TODAY_DATE;
    }

    public int getBORROW_NUMBER() {
        return BORROW_NUMBER;
    }

    public void setBORROW_NUMBER(int BORROW_NUMBER) {
        this.BORROW_NUMBER = BORROW_NUMBER;
    }

    public int getBOOK_NUMBER() {
        return BOOK_NUMBER;
    }

    public void setBOOK_NUMBER(int BOOK_NUMBER) {
        this.BOOK_NUMBER = BOOK_NUMBER;
    }

    public int getSTUDENT_ID() {
        return STUDENT_ID;
    }

    public void setSTUDENT_ID(int STUDENT_ID) {
        this.STUDENT_ID = STUDENT_ID;
    }

    public String getDUE_DATE() {
        return DUE_DATE;
    }

    public void setDUE_DATE(String DUE_DATE) {
        this.DUE_DATE = DUE_DATE;
    }

    public String getBOOK_NAME() {
        return BOOK_NAME;
    }

    public void setBOOK_NAME(String BOOK_NAME) {
        this.BOOK_NAME = BOOK_NAME;
    }
}
