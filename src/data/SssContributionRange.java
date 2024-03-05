package data;

public class SssContributionRange {
  private String range;
  private double contribution;

  public SssContributionRange(String range, double contribution) {
    this.range = range;
    this.contribution = contribution;
  }

  public String getRange() {
    return range;
  }

  public double getContribution() {
    return contribution;
  }
}
