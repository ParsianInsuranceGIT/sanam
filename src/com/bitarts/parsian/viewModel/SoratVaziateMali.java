package com.bitarts.parsian.viewModel;

import com.bitarts.parsian.model.asnadeSodor.Ghest;
import com.bitarts.parsian.model.asnadeSodor.Sanad;

/**
 * Created by IntelliJ IDEA.
 * User: Arron1
 * Date: 7/30/11
 * Time: 5:07 PM
 */
public class SoratVaziateMali {
    private Ghest ghest;
    private Sanad sanad;

    public Ghest getBedehi() {
        return ghest;
    }

    public void setBedehi(Ghest ghest) {
        this.ghest = ghest;
    }

    public Sanad getSanad() {
        return sanad;
    }

    public void setSanad(Sanad sanad) {
        this.sanad = sanad;
    }
}
