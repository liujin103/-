package com.example.quickindex;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import android.text.TextUtils;

public class PinYinUtil {
	/**
	 * 根据传入汉字，转为拼音,转拼音的操作有点耗内存，所以这个方法不应该被多次调用
	 * @param chinese
	 * @return
	 */
	public static String getPinYin(String chinese){
		if(TextUtils.isEmpty(chinese)) return "";
		
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();//用来设置转化拼音的格式
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);//转化的拼音是大写字母
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//转化的拼音不带声调
		
		//1.由于pinyin4j只能对单个汉字转化，不能直接转多个汉字，所以需要将字符串转为字符数组
		char[] charArray = chinese.toCharArray();
		String pinyin = "";
		//2.遍历字符数组，对每个汉字进行转化
		for (int i = 0; i < charArray.length; i++) {
			//小   马  -> xiaoma
			char ch = charArray[i];
			//3.过滤空格,忽略掉空格
			if(Character.isWhitespace(ch))continue;
			
			//a小2马*@@*  -> axiao2ma*@@*
			//4.判断是否是汉字，一个字节范围是-128~127，汉字是2个字节，所以汉字肯定大于127；
			//但是大于127不一定都是汉字;
			if(ch > 127){
				//可能是汉字
				try {
					//5.由于多音字的存在，所以会返回多个拼音， 单：dan  shan
					String[] pinyinArr = PinyinHelper.toHanyuPinyinStringArray(ch, format);
					if(pinyinArr!=null){
						pinyin += pinyinArr[0];//虽然多个拼音，但是仅能用第一个，
					}else {
						//转化失败，没有找到拼音，可以选择忽略也可以拼接
						pinyin += ch;
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
					//转化失败，说明不是汉字，可以选择拼接或者忽略，比如：O(∩_∩)O~
					pinyin += ch;
				}
			}else {
				//一般是英文，英文标点等键盘可以直接输入的字符，这个情况直接拼接
				pinyin += ch;
			}
		}
		return pinyin;
	}
}
