package hoanglong180903.myproject.internproject.model;

public class Scoring {
    private double queryScore;
    private FieldScore fieldScore;

    public double getQueryScore() {
        return queryScore;
    }

    public void setQueryScore(double queryScore) {
        this.queryScore = queryScore;
    }

    public FieldScore getFieldScore() {
        return fieldScore;
    }

    public void setFieldScore(FieldScore fieldScore) {
        this.fieldScore = fieldScore;
    }
}
