# LuckPan

幸运转盘，可以控制选中指定的奖项。如下图，选择 ID 为 6 的奖项，在转盘停止后，选中 ID 为 6 的奖项。

[demo下载](/apk/app-debug.apk)

![screenshot](/screenshots/screenshot.jpg)

最近项目中用到了转盘抽奖，才知道抽奖原来这么坑，转盘转动只是给用户看看而已。和点击按钮直接显示出抽奖结果的区别就是等、等、等，看着转盘揪心。

需求：

1. 转盘上奖项的个数以及奖项的值通过调用接口确定
2. 点击“开始抽奖”按钮，调接口成功后，停在接口返回的指定位置

API:

| 方法          | 参数           | 说明  |
| :-------------|:-------------| :-----|
| setPrizeVoList(List\<PrizeVo\>) | PrizeVo 实体集合，PrizeVo 实体包含 ID、title 等属性 | (必须)设置奖项集合 |
| setDarkColor(int) | 颜色，如 Color.rgb(82, 182, 197) | (非必须,有默认)设置转盘上的深色 |
| setShallowColor(int) | 颜色，如 Color.rgb(186, 226, 232) | (非必须,有默认)设置转盘上的浅色色 |
| setCircleNumRange(int, int) | 转动圈数的范围，如 9～15 | (非必须,有默认)设置转动圈数的范围 |
| setOneCircleMillisRange(long, long) | 每圈平均用时毫秒数的范围，如 400～600 | (非必须,有默认)设置平均转动一圈用时的范围 |
| setOnLuckPanAnimatorEndListener(listener)  | 转盘停止监听器 | (非必须)设置转盘停止监听器 |
| start(int) | 选中奖项的 ID | (必须)转盘开始转动，并停在 ID 值对应的位置上 |

## 使用

1.得到奖项数据集合

```java
List<PrizeVo> prizeVoList = new ArrayList<>();
PrizeVo prizeVo;
for (int i = 0; i < 16; i++) {
    prizeVo = new PrizeVo();
    prizeVo.id = i + "";
    prizeVo.rate = i + "";
    prizeVo.title = "×" + i;
    prizeVoList.add(prizeVo);
}
```

2.转盘设置奖项数据

```java
// 转盘设置奖项集合
mLuckPan.setPrizeVoList(prizeVoList);
```

3.开始转动

```java
// 转到 ID 为 3 的奖项停止
mLuckPan.start(3);
```

可选：设置转动停止监听器

```java
mLuckPan.setOnLuckPanAnimatorEndListener(new LuckPan.OnLuckPanAnimatorEndListener() {
    @Override
    public void onLuckPanAnimatorEnd(PrizeVo choicePrizeVo) {
        Toast.makeText(MainActivity.this, "选中的ID：" + choicePrizeVo.id, Toast.LENGTH_SHORT).show();
    }
});
```

