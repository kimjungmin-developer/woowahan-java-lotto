package lotto.model;

import java.util.List;

public class LottoResult {
    private Money money;
    private PrizeInfo prizeInfo;


    public LottoResult(Money money, List<Prize> prizes) {
        prizeInfo = new PrizeInfo(prizes);
        this.money = money;
    }

    public double calculateProfitRate() {
        double totalPrizeMoney = calculateTotalPrizeMoney();
        return totalPrizeMoney / money.getMoney();
    }

    public double calculateTotalPrizeMoney() {
        double sum = 0;
        for (Prize prize : Prize.values()) {
            sum += prize.getPrizeMoney() * prizeInfo.get(prize);
        }
        return sum;
    }

    public int getCount(Prize prize) {
        return prizeInfo.get(prize);
    }
}
