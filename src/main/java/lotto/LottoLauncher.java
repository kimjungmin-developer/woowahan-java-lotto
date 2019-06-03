package lotto;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoLauncher {
    private static final int STARTING_COUNT = 0;

    public static void main(String[] args) {
        Money money = generateMoney();
        ManualLottoCount manualLottoCount = generateManualLottoCount(money);
        Lottos lottos = Wallet.buyLottos(money, prepareManualLottos(manualLottoCount));

        OutputView.printLottos(money, manualLottoCount, lottos);

        WinningLotto winningLotto = generateWinningLotto();
        LottoResult lottoResult = new LottoResult(money, lottos.getPrizes(winningLotto));

        OutputView.printStatistics(lottoResult);
    }

    private static List<Lotto> prepareManualLottos(ManualLottoCount manualLottoCount) {
        try {
            List<Lotto> manualLottos = new ArrayList<>();
            int currentCount = STARTING_COUNT;
            while (manualLottoCount.isCountFinished(currentCount)) {
                manualLottos.add(new Lotto(InputView.askManualLottoNumbers()));
                currentCount++;
            }
            return manualLottos;
        } catch (Exception e) {
            System.out.println("수동로또 입력값중 잘못된 부분이 있습니다!");
            return prepareManualLottos(manualLottoCount);
        }

    }

    private static WinningLotto generateWinningLotto() {
        try {
            Lotto winningLottoTicket = new Lotto(InputView.askWinningLottoNumbers());
            LottoNumber bonusNumber = new LottoNumber(InputView.askBonusNumber());
            return new WinningLotto(winningLottoTicket, bonusNumber);
        } catch (Exception e) {
            System.out.println("잘못된 번호 입력이 있습니다!");
            return generateWinningLotto();
        }
    }

    private static ManualLottoCount generateManualLottoCount(Money money) {
        try {
            int input = InputView.askManualLottoCount();
            return new ManualLottoCount(input, money);
        } catch (Exception e) {
            return generateManualLottoCount(money);
        }
    }

    private static Money generateMoney() {
        try {
            String userInput = InputView.askMoney();
            return new Money(userInput);
        } catch (Exception e) {
            System.out.println("잘못된 구입 금액입니다");
            return generateMoney();
        }
    }

}
