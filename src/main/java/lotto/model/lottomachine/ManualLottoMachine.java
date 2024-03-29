package lotto.model.lottomachine;

import lotto.model.Lotto;

public class ManualLottoMachine implements LottoMachine {
    private static final String DELIMITER = ",";

    private String currentManualLotto;

    public ManualLottoMachine(String currentManualLotto) {
        this.currentManualLotto = currentManualLotto;
    }

    @Override
    public Lotto generateLotto() {
        String copiedCurrentManualLotto = new String(this.currentManualLotto);
        return new Lotto(copiedCurrentManualLotto);
    }

}
