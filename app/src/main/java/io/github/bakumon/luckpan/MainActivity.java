package io.github.bakumon.luckpan;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.bakumon.luckpan.entity.PrizeVo;
import io.github.bakumon.luckpan.view.LuckPan;

public class MainActivity extends AppCompatActivity implements LuckPan.OnLuckPanAnimatorEndListener {

    private LuckPan mLuckPan;
    private NumberPicker mNpChoiceID;
    private ImageView mIvStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLuckPan = (LuckPan) findViewById(R.id.luck_pan);
        mNpChoiceID = (NumberPicker) findViewById(R.id.np_choice_id);
        mIvStart = (ImageView) findViewById(R.id.iv_start);

        List<PrizeVo> prizeVoList = new ArrayList<>();
        PrizeVo prizeVo;
        for (int i = 0; i < 16; i++) {
            prizeVo = new PrizeVo();
            prizeVo.id = i + "";
            prizeVo.rate = i + "";
            prizeVo.title = "×" + i;
            prizeVoList.add(prizeVo);
        }
        //使 NumberPicker 不弹出输入框
        mNpChoiceID.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        // NumberPicker 每个数字对应转盘上奖项的 ID
        mNpChoiceID.setMaxValue(prizeVoList.size() - 1);

        // 给转盘设置奖项集合
        mLuckPan.setPrizeVoList(prizeVoList);
        // 设置转盘交替的深色
        mLuckPan.setDarkColor(Color.rgb(82, 182, 197));
        // 设置转盘交替的浅色
        mLuckPan.setShallowColor(Color.rgb(186, 226, 232));
        // 给转盘设置动画结束的监听
        mLuckPan.setOnLuckPanAnimatorEndListener(this);
        // 设置转动圈数的范围
        mLuckPan.setCircleNumRange(9, 15);
        // 设置平均转动一圈用时
        mLuckPan.setOneCircleMillisRange(400, 600);


        mIvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIvStart.setEnabled(false);
                mLuckPan.start(mNpChoiceID.getValue());
            }
        });

    }

    @Override
    public void onLuckPanAnimatorEnd(PrizeVo choicePrizeVo) {
        mIvStart.setEnabled(true);
        Toast.makeText(this, "选中的ID：" + choicePrizeVo.id, Toast.LENGTH_SHORT).show();
    }
}
