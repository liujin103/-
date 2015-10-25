package com.example.quickindex;


public class Friend implements Comparable<Friend>{
private String name;
private String pinyin;

public Friend(String name) {
	super();
	this.name=name;
	//心得：在数据创建的时候去处理，而不是每次需要的时候去处理，前提是数据不变
			setPinyin(PinYinUtil.getPinYin(name));
		}
	

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
//排序  需要Friend类实现Comparable重写compareTo
@Override
public int compareTo(Friend another) {
//String pinYin=PinYinUtil.getPinYin(name);
//String anotherPinYin=PinYinUtil.getPinYin(another.getName());
	//return pinYin.compareTo(anotherPinYin);
	return getPinyin().compareTo(another.getPinyin());
}

public String getPinyin() {
	return pinyin;
}

public void setPinyin(String pinyin) {
	this.pinyin = pinyin;
}


}
