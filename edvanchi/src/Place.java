

/**
 * Created by edvanchi on 21.02.2017.
 */
public enum Place {
    Water(true, "вода"),
    Moss(false, "мох");

    private String substance;
    private boolean isLiquid;

    private Place(boolean isLiquid, String substance) {
        this.isLiquid = isLiquid;
        this.substance = substance;
    }
    public Boolean getLiquid() {
        return isLiquid;
    }
    public void setLiquid(boolean liquid) {
        this.isLiquid = liquid;
    }
    public String getSubstance() {
        return substance;
    }
    public void setSubstance(String substance) {
        this.substance = substance;
    }
}
