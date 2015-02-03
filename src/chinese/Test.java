package chinese;

import java.io.UnsupportedEncodingException;

public class Test {
	// 发代码之前先转段预备知识
	//
	// 计算机处理汉字信息的前提条件是对每个汉字进行编码，这些编码统称为汉字编码。
	// 汉字信息在系统内传送的过程就是汉字编码转换的过程。
	// 汉字交换码：汉字信息处理系统之间或通信系统之间传输信息时，对每一个汉字所规定的统一编码，
	// 我国已指定汉字交换码的国家标准“信息交换用汉字编码字符集——基本集”，代号为GB2312—80，又称为“国标码”。
	// 国标码：所有汉字编码都应该遵循这一标准，汉字机内码的编码、汉字字库的设计、汉字输入码的转换、输出设备的汉字地址码等，都以此标准为基础。
	// GB2312—80就是国标码。该码规定：一个汉字用两个字节表示，每个字节只有7位，与ASCII码相似。
	// 区位码：将GB2312—80的全部字符集组成一个94×94的方阵，每一行称为一个“区”，编号为0l～94；每一列称为一个“位”，编号为0l～94，这样得到
	// GB2312—80的区位图，用区位图的位置来表示的汉字编码，称为区位码。
	// 机内码：为了避免ASCII码和国标码同时使用时产生二义性问题，大部分汉字系统都采用将国标码每个字节高位置1作为汉字机内码。
	// 这样既解决了汉字机内码与西文机内码之间的二义性，又使汉字机内码与国标码具有极简单的对应关系。
	// 汉字机内码、国标码和区位码三者之间的关系为：区位码（十进制）的两个字节分别转换为十六进制后加20H得到对应的国标码；
	// 机内码是汉字交换码（国标码）两个字节的最高位分别加1，即汉字交换码（国标码）的两个字节分别加80H得到对应的机内码；
	// 区位码（十进制）的两个字节分别转换为十六进制后加A0H得到对应的机内码。

//	GB2312 94*94的方阵，一个汉字由两个字节构成，第一个字节称为区码，相当于方阵的行，第二个字节称为位码，相当于方阵的列
//	区号： 01-09 代表特殊字符，16-55 代表一级汉字，是常见的汉字，56-87代表二级汉字，方阵是72*94，共6763个汉字，基本满足99.5%中文使用
	
	public String bytes2HexString(byte b) {
		return bytes2HexString(new byte[] { b });
	}

	// 汉字转换成区位码
	public String bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}

	// 汉字转换成区位码
	public String getString(String chinese) {
		byte[] bs;
		String s = "";
		try {
			bs = chinese.getBytes("GB2312");

			for (int i = 0; i < bs.length; i++) {
				int a = Integer.parseInt(bytes2HexString(bs[i]), 16);
				s += (a - 0x80 - 0x20) + "";
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
	}

	// 区位码转换成汉字
	public String CodeToChinese(String code) {
		String Chinese = "";
		for (int i = 0; i < code.length(); i += 4) {
			byte[] bytes = new byte[2];
			String lowCode = code.substring(i, i + 2);
			int tempLow = Integer.parseInt(lowCode);
			tempLow += 160;
			bytes[0] = (byte) tempLow;
			String highCode = code.substring(i + 2, i + 4);
			int tempHigh = Integer.parseInt(highCode);
			tempHigh += 160;
			bytes[1] = (byte) tempHigh;
			String chara = new String(bytes);
			Chinese += chara;
		}
		return Chinese;
	}

	// 测试
	public static void main(String[] args) throws Exception {
		String str = "创";
		Test test = new Test();
		String s = test.getString(str);
		System.out.println(s);
		String a = test.CodeToChinese(s);
		System.out.println(a);
	}
}