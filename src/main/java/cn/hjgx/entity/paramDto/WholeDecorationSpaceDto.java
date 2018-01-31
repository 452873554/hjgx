package cn.hjgx.entity.paramDto;

import cn.hjgx.entity.WholeDecorationItem;
import cn.hjgx.entity.WholeDecorationSpace;

import java.util.List;

/**
 * Created by alvin on 2018/1/30.
 */
public class WholeDecorationSpaceDto extends WholeDecorationSpace {

    private List<WholeDecorationItemDto> wholeDecorationItemDtos;

    public List<WholeDecorationItemDto> getWholeDecorationItemDtos() {
        return wholeDecorationItemDtos;
    }

    public void setWholeDecorationItemDtos(List<WholeDecorationItemDto> wholeDecorationItemDtos) {
        this.wholeDecorationItemDtos = wholeDecorationItemDtos;
    }
}
