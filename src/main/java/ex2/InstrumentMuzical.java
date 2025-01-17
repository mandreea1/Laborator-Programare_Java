package ex2;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public abstract class InstrumentMuzical {
    private String producator;
    private int pret;
    public InstrumentMuzical() {}
    public InstrumentMuzical(String producator, int pret) {
        this.producator = producator;
        this.pret = pret;
    }
    public String getProducator() { return producator; }
    public int getPret() { return pret; }
    public void setPret(int pret) { this.pret = pret; }
    public void setProducator(String producator) { this.producator = producator; }

    @Override
    public String toString() {
        return producator+", "+pret;
    }

}
