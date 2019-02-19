package company;

class TaxBracket{
    private int minIncome;
    private int maxIncome;
    private int baseTax;
    private double taxRate;

    public TaxBracket(int minIncome, int maxIncome, int baseTax, double taxRate) {
        this.minIncome = minIncome;
        this.maxIncome = maxIncome;
        this.baseTax = baseTax;
        this.taxRate = taxRate;
    }

    int getMinIncome() {
        return minIncome;
    }

    int getMaxIncome() {
        return maxIncome;
    }

    int getBaseTax() {
        return baseTax;
    }

    double getTaxRate() {
        return taxRate;
    }
}
