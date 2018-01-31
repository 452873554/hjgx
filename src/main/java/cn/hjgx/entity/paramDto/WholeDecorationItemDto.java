package cn.hjgx.entity.paramDto;

import cn.hjgx.entity.WholeDecorationItem;
import cn.hjgx.entity.WholeDecorationSpu;

import java.util.List;

/**
 * Created by alvin on 2018/1/30.
 */
public class WholeDecorationItemDto extends WholeDecorationItem {

    private List<WholeDecorationSpu> wholeDecorationSpus;

    public List<WholeDecorationSpu> getWholeDecorationSpus() {
        return wholeDecorationSpus;
    }

    public void setWholeDecorationSpus(List<WholeDecorationSpu> wholeDecorationSpus) {
        this.wholeDecorationSpus = wholeDecorationSpus;
    }
}
