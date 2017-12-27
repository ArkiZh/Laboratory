package com.arki.laboratory.snippet;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

public class StringCutter {

	private static String SPARATOR_LINE = System.getProperty("row.separator", "\n");
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Species species = new Species(1, "鹈鹕", "一种很高级的鸟类，会飞会叫会下蛋！你值得拥有");
		Species species2 = new Species(2, "大熊猫"+SPARATOR_LINE+"Panda", "大熊猫（学名：Ailuropoda melanoleuca，英文名称：Giant panda），属于哺乳纲、食肉目、熊科，是大熊猫亚科和大熊猫属唯一哺乳动物。大熊猫已在地球上生存了至少800万年，被誉为“活化石”和“中国国宝”，世界自然基金会的形象大使，是世界生物多样性保护的旗舰物种。据第四次全国大熊猫野外种群调查结果，野生大熊猫仅有1864只，其中80%以上分布于四川境内，属于中国国家一级保护动物。截止2015年底，全世界圈养单位共圈养熊猫425只。大熊猫最初是吃肉的，经过进化，99%的食物都是竹子了，但牙齿和消化道还保持原样，仍然划分为食肉目，发怒时危险性堪比其它熊种。野外大熊猫的寿命为18～20岁，圈养状态下可以超过30岁。");
		Species species3 = new Species(3, "水牛", "水牛属（学名：Bubalus）：共有4种，水牛（野水牛）为亚洲普遍畜力，野生种群分布于印度、尼泊尔、不丹和泰国以及澳大利亚北部，数量十分稀少，三者体型较小，曾经另置于倭水牛属（Anoa），《世界自然保护联盟》（IUCN）将这两个属均并入“水牛属”，注明分类有争议。");
		List<Species> speciesList = new ArrayList<Species>();
		speciesList.add(species);
		speciesList.add(species2);
		speciesList.add(species3);
		String[] columnNames = {"index","name","description"};
		Double[] widthOfColumns = {4.0,6.0,100.0};
		Double charWidth = 1.0;
		Double wordWidth = 2.0;
		Integer[] rowsOfPages = {7,8,1};
		/*Map<String, Object> outputListHandler = outputListHandler(speciesList, columnNames,widthOfColumns,charWidth,wordWidth);
		for (Map.Entry<String, Object> entry : outputListHandler.entrySet()) {
			System.out.println(entry.getKey()+"------------------------------");
			Object[] tempArr =  (Object[]) entry.getValue();
			System.out.println(tempArr[0]);
		}*/
		Map<String, Object> pageMap = outputListHandlerPro(speciesList, columnNames,widthOfColumns,rowsOfPages,charWidth,wordWidth);
		for (Map.Entry<String, Object> entry : pageMap.entrySet()) {
			String columnName = entry.getKey();
			List<Object> columnInfoList = (List<Object>) entry.getValue();
			System.out.println(columnName+"======================================================");
			System.out.println("总页数："+(Integer)columnInfoList.get(0));
			for (int i=1;i<columnInfoList.size();i++) {
				Object[] columnOfCurrentPage = (Object[]) columnInfoList.get(i);
				System.out.println("第"+i+"页"+columnName+"列数据：");
				System.out.println((String)columnOfCurrentPage[0]);
			}
		}
	}
	
	/**
	 * 
	 * Method description : 将对象列表中的成员变量裁剪成对齐格式
	 *
	 * Author：        kai                
	 * Create Date：   2017年7月3日 下午11:14:24
	 *
	 * @param srcVoList			要转换的对象列表
	 * @param columnNames		要转换的对象成员变量名
	 * @param widthOfColumns	各个成员变量对应的列宽
	 * @param charWidth			英文字符的宽度
	 * @param wordWidth			中文字符的宽度
	 * @param rowsOfPages		每一页的行数，[8,10,9]:代表首页8行，中间页10行，末页9行。
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	public static <T> Map<String, Object> outputListHandlerPro(List<T> srcVoList, String[] columnNames,Double[] widthOfColumns,Integer[] rowsOfPages,Double charWidth,Double wordWidth){
		
		if(srcVoList==null || srcVoList.size()==0) return null;
		
		//获取泛型类
		Class<? extends Object> clazz = srcVoList.get(0).getClass();

		//单独断行各个对象成员变量：
		List<Map<String,Object>> rowMapList = new ArrayList<Map<String,Object>>();
		//遍历对象列表
		for (int i=0;i< srcVoList.size();i++) {
			T srcVo = srcVoList.get(i);
			//遍历当前对象各个成员变量
			Map<String,Object> tempRowMap = new HashMap<String, Object>();
			for (int j=0 ;j<columnNames.length;j++) {
				String columnName = columnNames[j];
				try {
					//获取字段值
					Field field = clazz.getDeclaredField(columnName);
					field.setAccessible(true);
					String value = field.get(srcVo).toString();
					
					//单独处理当前分行
					Map<String, Object> cellMap = outputStringHandler(value, widthOfColumns[j], charWidth, wordWidth);
					tempRowMap.put(columnName, cellMap);
				} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			rowMapList.add(tempRowMap);
		}
		
		//处理数据的分页
		//初始化各列数据，防止空指针
		Map<String,Object> pageMap = new HashMap<String, Object>();
		for (String columnName : columnNames) {
			List<Object> columnInfoList = new ArrayList<Object>();
			columnInfoList.add(1);
			Object[] tempArr = {"",0};
			columnInfoList.add(tempArr);
			pageMap.put(columnName, columnInfoList);	//第0个元素：页数，第1个元素：第1页列数据，第2个元素：第2页列数据
		}
		
		int maxRowNum = 0;
		int pageNum = 1;
		//遍历每一行
		for (int i = 0; i < rowMapList.size(); i++) {
			//计算每一行的最大行
			int currentRowCountMax = 1;
			Map<String, Object> tempRowMap = rowMapList.get(i);
			for (Map.Entry<String, Object> entry : tempRowMap.entrySet()) {
				Map<String, Object> cellMap = (Map<String, Object>) entry.getValue();
				Integer rowCount = (Integer) cellMap.get("rowCount");
				if(rowCount>currentRowCountMax) currentRowCountMax=rowCount;
			}
			maxRowNum += currentRowCountMax+1;	//加上结尾的空一行
			
			if(maxRowNum-1 > rowsOfPages[pageNum==1?0:1]) {	//超出页面范围加一页
				pageNum++;
				maxRowNum = currentRowCountMax+1;
				for (Map.Entry<String, Object> entry : pageMap.entrySet()) {
					List<Object> columnInfoList = (List<Object>) entry.getValue();
					Object[] tempArr = {"",0};
					columnInfoList.add(tempArr);
				}
			}
			
			for (Map.Entry<String, Object> entry : tempRowMap.entrySet()) {
				Map<String, Object> cellMap = (Map<String, Object>) entry.getValue();
				String columnName = entry.getKey();
				String resultStr = (String) cellMap.get("resultStr");
				Integer rowCount = (Integer) cellMap.get("rowCount");
				
				List<Object> columnInfoList = (List<Object>)pageMap.get(columnName);
				columnInfoList.set(0, pageNum);
				Object[] columnOfCurrentPage = (Object[]) columnInfoList.get(pageNum);
				columnOfCurrentPage[1]=rowCount;
				StringBuilder sb = new StringBuilder((String)columnOfCurrentPage[0]).append(resultStr);
				for (int j = 0; j < currentRowCountMax+1 - rowCount; j++) sb.append(SPARATOR_LINE);
				columnOfCurrentPage[0] = sb.toString();
			}
			
		}
		
		
		
		return pageMap;
	}
	
	

	/**
	 * 
	 * Method description : 将对象列表中的成员变量裁剪成对齐格式
	 *
	 * Author：        kai                
	 * Create Date：   2017年7月3日 下午11:14:24
	 *
	 * @param srcVoList			要转换的对象列表
	 * @param columnNames		要转换的对象成员变量名
	 * @param widthOfColumns	各个成员变量对应的列宽
	 * @param charWidth			英文字符的宽度
	 * @param wordWidth			中文字符的宽度
	 * @return
	 *
	 */
	public static <T> Map<String, Object> outputListHandler(List<T> srcVoList, String[] columnNames,Double[] widthOfColumns,Double charWidth,Double wordWidth){
		if(srcVoList==null || srcVoList.size()==0) return null;
		
		Map<String,Object> map = new HashMap<String, Object>();
		for (String columnName : columnNames) {
			Object[] tempArr = {"",0};
			map.put(columnName, tempArr);
		}
		
		int baseRowNum = 0;
		Class<? extends Object> clazz = srcVoList.get(0).getClass();
		
		for (T srcVo : srcVoList) {
			int currentRowCountMax = 1;
			for (int i=0 ;i<columnNames.length;i++) {
				String columnName = columnNames[i];
				try {
					//获取字段值
					Field field = clazz.getDeclaredField(columnName);
					field.setAccessible(true);
					String value = field.get(srcVo).toString();
					
					//单独处理当前分行
					Map<String, Object> tempMap = outputStringHandler(value, widthOfColumns[i], charWidth, wordWidth);
					
					//根据历史追加分行
					Integer rowCount = (Integer) tempMap.get("rowCount");
					StringBuilder sb = new StringBuilder();
					Object[] historyResult = (Object[]) map.get(columnName);
					Integer historyRowCount = (Integer) historyResult[1];
					sb.append((String)historyResult[0]);
					for (int j = 0; j < baseRowNum - historyRowCount; j++) sb.append(SPARATOR_LINE);
					sb.append((String) tempMap.get("resultStr")).append(SPARATOR_LINE).append(SPARATOR_LINE);
					
					//填充map
					Object[] tempArr = {sb.toString(),baseRowNum+rowCount};
					map.put(columnName, tempArr);
					if(rowCount>currentRowCountMax) currentRowCountMax=rowCount;
				} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			baseRowNum += currentRowCountMax;
		}
		return map;
	}
	
	
	/**
	 * 
	 * Method description : 根据行宽裁剪对齐字符串
	 *
	 * Author：       kai                
	 * Create Date：   2017年7月3日 下午8:13:46
	 *
	 * @param srcStr	源字符串
	 * @param rowWidth	行宽			default=100.0
	 * @param charWidth	英文字符宽度	default=1.0
	 * @param wordWidth	中文字符宽度	default=2.0
	 * @return
	 *
	 */
	public static Map<String, Object> outputStringHandler(String srcStr,Double rowWidth,Double charWidth,Double wordWidth){
		rowWidth = rowWidth==null?100.0:rowWidth;
		charWidth = charWidth==null?1.0:charWidth;
		wordWidth = wordWidth==null?2.0:wordWidth;
		double width = 0;
		int rowCount = 1;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < srcStr.length(); i++) {
			if(srcStr.indexOf(SPARATOR_LINE)==i){
				i += SPARATOR_LINE.length()-1;
				sb.append(SPARATOR_LINE);
				width=0.0;
				rowCount++;
				continue;
			}
			char c = srcStr.charAt(i);
			width += (c >=0 && c<=255) ? charWidth : wordWidth;
			if(width<=rowWidth){
				sb.append(c);
			}else{
				sb.append(SPARATOR_LINE).append(c);
				width=(c >=0 && c<=255) ? charWidth : wordWidth;
				rowCount++;
			}
		}
		sb.append(SPARATOR_LINE);
		Map<String,Object> map = new HashMap<String,Object> ();
		map.put("resultStr", sb.toString());
		map.put("rowCount", rowCount);
		return map;
	}
	
	@Test
	public void tempTest(){
		//Scanner scanner = new Scanner(System.in);
		//String rowSeparator = System.getProperty("row.separator", "\n");
		//String srcStr = scanner.nextRow();
		//printCode(srcStr);
		String srcStr = "abczABCZ.,:?-_+=/阿伯瓷的额佛歌，。：？——-+=、/ a";
		Double rowWidth = 5.0;
		Map<String, Object> resultMap = outputStringHandler(srcStr,rowWidth,null,null);
		System.out.println(resultMap);
	}
	
	public static void printCode(String srcStr){
		System.out.println(SPARATOR_LINE+SPARATOR_LINE+"------------------------------------------------------------printCode:start");
		System.out.println("srcStr=\""+srcStr+"\",length="+srcStr.length());
		
		char[] charArray = srcStr.toCharArray();
		
		System.out.println("序号\t字符\tunicode\t是否大写\t字节");
		for (int i = 0; i < srcStr.length(); i++) {
			System.out.print(i+"\t");
			System.out.print(srcStr.charAt(i)+"\t");
			System.out.print(Character.codePointAt(charArray, i)+"\t");
			System.out.print(Character.isUpperCase(charArray[i])+"\t");
			System.out.print(Arrays.toString(srcStr.substring(i, i+1).getBytes())+"\t");
			System.out.println();
		}
		System.out.println("-------------------------------------------------------------printCode:end"+SPARATOR_LINE+SPARATOR_LINE);
	}
}

class Species{
	private int index;
	private String name;
	private String description;
	public Species(int index, String name, String description) {
		super();
		this.index = index;
		this.name = name;
		this.description = description;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}