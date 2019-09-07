package cn.itcast.drawing.shape;

import java.lang.annotation.ElementType;

import com.sun.prism.paint.Color;

public class Shape {
	   protected ElementType Type;//图元类型
	   protected int OrgX;//原点坐标
	   protected int OrgY;
	   protected Color   BorderColor;//边界颜色
	   protected int BorderType;//边界线型--实线、虚线、虚点线等
	   protected int BorderWidth;//边界宽度
	   protected Color  FillColor;//填充颜色
	   protected int FillType;//填充类型--实心、双对角、十字交叉等
  Shape(){}
  public void Draw(CDC pDC)
  {
	  pDC=null;
  }
  //绘制图元
  public Boolean IsMatched(CPoint pnt) {
	return null;
  }//点是否落在图形内部
 public void Serialize(CArchive ar) {
	 ar=null;
 }
 public void SetValue(ElementType t, int x, int y, int width, int height) {
	 t=null;x=y=width=height=0;
 }
  
public void SetPen(Color bcolor, int btype, int bwidth)  {
	BorderColor = bcolor;BorderType = btype; BorderWidth = bwidth;
}
	void GetPen(Color bcolor, int btype, int bwidth) {
		bcolor = BorderColor;btype = BorderType;bwidth = BorderWidth;
	}
	void SetBrush(Color fcolor, int ftype) {
		FillColor = fcolor;FillType = ftype;
	}
	void GetBrush(Color fcolor, int ftype) {
		fcolor = FillColor;ftype = FillType;
	}
	public void GetValue(ElementType t, int x, int y, int width, int height) //获取数据成员的值
	{
		t=null;x=y=width=height=0;
	}
    
}


class CSquare extends Shape{
	private static final int HS_HORIZONTAL = 0;
	private static final int HS_DIAGCROSS = 0;
	CSquare(){}
	private int width,w,h;
	CSquare(int x, int y, int w){
		OrgX = x;//点坐标
		OrgY = y;
		width = w;
		BorderColor = RGB(255, 0, 0);//边界颜色
		BorderType = 0;//边界线型--实线、虚线、虚点线等
		BorderWidth = 25;//边界宽度
		FillColor = RGB(0, 0, 255);//填充颜色
		FillType = 1;//填充类型--实心、双对角、十字交叉等
	}
	private Color RGB(int i, int j, int k) {
		// TODO 自动生成的方法存根
		return null;
	}
	
	public void Draw(CDC pDC)//绘制正方形
	{
		CPen pen = null, pOldPen;//新、老画笔
		pen.CreatePen(BorderType, BorderWidth, BorderColor);//创建画笔（类型、颜色、宽度）
		pOldPen = (CPen)pDC.SelectObject(pen);//选择新画笔并保存老画笔
		CBrush brush = null, pOldBrush;//新老画刷
		if (FillType >= HS_HORIZONTAL && FillType <= HS_DIAGCROSS)
			brush.CreateHatchBrush(FillType, FillColor);//填充类型为用户自定义填充类型
		else
			brush.CreateSolidBrush(FillColor); //填充类型为默认白色填充类型
		pOldBrush = (CBrush)pDC.SelectObject(brush);

		pDC.Rectangle(OrgX - width / 2, OrgY - width / 2, OrgX + width / 2, OrgY + width / 2);//绘制正方形

		pDC.SelectObject(pOldPen);//恢复老画笔、老画刷。
		pDC.SelectObject(pOldBrush);
	}
	
    public Boolean IsMatched(CPoint pnt)
    {
    	if ((pnt.x >= OrgX - width / 2) &&(pnt.x <= OrgX + width / 2) && (pnt.y >= OrgY - width / 2) && (pnt.y <= OrgY + width / 2))
    		return true;
    	else
    		return false;
    }
	public void Serialize(CArchive ar)//序列化正方形图元
	{
		
	}
	public void SetValue(ElementType t, int x, int y, int width, int height) {
		Type = t; OrgX = x;OrgY = y;width = w;
	}
	
	public void GetValue(ElementType t, int x, int y, int width, int height) {
		t = Type;x = OrgX;y = OrgY;w = width;h = 0;
	}

}

class CRectangle extends Shape{
	private static final ElementType RECTANGLE = null;
	private static final int PS_SOLID = 0;
	private static final int HS_HORIZONTAL = 0;
	private static final int HS_DIAGCROSS = 0;
	int width;
	int height;
	int w,h;
	Color   BorderColor;//边界颜色
	int BorderType;//边界线型--实线、虚线、虚点线等
	int BorderWidth;//边界宽度
	Color  FillColor;//填充颜色
	int FillType;//填充类型--实心、双对角、十字交叉

	CRectangle(){}
	CRectangle(int x, int y, int w, int h){
	Type = RECTANGLE;
	OrgX = x;//原点坐标
	OrgY = y;
	BorderColor = RGB(255, 0, 0);//边界颜色
	BorderType = PS_SOLID;//边界线型--实线、虚线、虚点线等
	BorderWidth = 1;//边界宽度
	FillColor = RGB(0, 255, 0);//填充颜色
	FillType = HS_HORIZONTAL;//填充类型--实心、双对角、十字交叉等
	width = w; height = h;
	}
	private Color RGB(int i, int j, int k) {
		// TODO 自动生成的方法存根
		return null;
	}
	
	public void Draw(CDC pDC)//绘制正方形
	{
		CPen pen = null, pOldPen;
		pen.CreatePen(BorderType, BorderWidth, BorderColor);
		pOldPen = (CPen)pDC.SelectObject(pen);
		CBrush brush = null, pOldBrush;
		if (FillType >= HS_HORIZONTAL && FillType <= HS_DIAGCROSS)
			brush.CreateHatchBrush(FillType, FillColor);
		else
			brush.CreateSolidBrush(FillColor);
		pOldBrush = (CBrush)pDC.SelectObject(brush);

		pDC.Rectangle(OrgX - width / 2, OrgY - height / 2, OrgX + width / 2, OrgY + height / 2);

		pDC.SelectObject(pOldPen);
		pDC.SelectObject(pOldBrush);

	}
	public Boolean IsMatched(CPoint pnt)//重载点pnt是否落在图元内
	{
		if ((pnt.x >= OrgX - width / 2) && (pnt.x <= OrgX + width / 2) && (pnt.y >= OrgY - height / 2) && (pnt.y <= OrgY + height / 2))
			return true;
		else
			return false;

	}
	public void Serialize(CArchive ar)//序列化正方形图元
	{
		
	}
	public void SetValue(ElementType t, int x, int y, int width, int height)//设置长方形对象中的部分数据成员（与画笔和画刷无关的）的值
	{
		Type = t; OrgX = x;OrgY = y;width = w;height = h;
	}
	public void GetValue(ElementType t, int x, int y, int width, int height) {
		t = Type;x = OrgX;y = OrgY;w = width; h = height;
	}
	
}


class CTriangle extends Shape{
	private static final int HS_HORIZONTAL = 0;
	private static final int HS_DIAGCROSS = 0;
	int width;
	int length;
	
	CTriangle(){
		
	}
	CTriangle(int x, int y, int w){
		OrgX = x;//点坐标
		OrgY = y;
		width = w;
		BorderColor = RGB(1, 1, 1);//边界颜色
		BorderType = 0;//边界线型--实线、虚线、虚点线等
		BorderWidth = 2;//边界宽度
		FillColor = RGB(255, 255, 255);//填充颜色
		FillType = 1;//填充类型--实心、双对角、十字交叉等
	}
	private Color RGB(int i, int j, int k) {
		// TODO 自动生成的方法存根
		return null;
	}
	public void Draw(CDC pDC)//绘制三角形
	{
		CPen pen = null, pOldPen;//新、老画笔
		pen.CreatePen(BorderType, BorderWidth, BorderColor);//创建画笔（类型、颜色、宽度）
		pOldPen = (CPen)pDC.SelectObject(pen);//选择新画笔并保存老画笔
		CBrush brush = null, pOldBrush;//新老画刷
		if (FillType >= HS_HORIZONTAL && FillType <= HS_DIAGCROSS)
			brush.CreateHatchBrush(FillType, FillColor);//填充类型为用户自定义填充类型
		else
			brush.CreateSolidBrush(FillColor); //填充类型为默认白色填充类型
		pOldBrush = (CBrush)pDC.SelectObject(brush);

		int x1 = OrgX - width * 58 / 100;				//三角形的三个点
		int y1 = OrgY + width / 3;
		int x2 = OrgX + 58 * width / 100;
		int y2 = OrgY + width / 3;
		int x3 = OrgX;
		int y3 = OrgY - width * 2 / 3;
		CPoint a[] = null ;
		pDC.Polygon(a, 3);
		pDC.SelectObject(pOldPen);//恢复老画笔、老画刷。
		pDC.SelectObject(pOldBrush);

	}
	
	public Boolean IsMatched(CPoint pnt)//重载点pnt是否落在图元内
	{
		int x1 = OrgX - width * 58 / 100;				//三角形的三个点
		int y1 = OrgY + width / 3;
		int x2 = OrgX + 58 * width / 100;
		int y2 = OrgY + width / 3;
		int x3 = OrgX;
		int y3 = OrgY - width * 2 / 3;

		int c1 = (pnt.x - x1)*(pnt.x - x1) + (pnt.y - y1)*(pnt.y - y1);
		int c2 = (pnt.x - x2)*(pnt.x - x2) + (pnt.y - y2)*(pnt.y - y2);
		int c3 = (x1 - x3)*(x1 - x3) + (y1 - y3)*(y1 - y3);
		int c4 = (x2 - x3)*(x2 - x3) + (y2 - y3)*(y2 - y3);

		if (c1 + c2<c3 + c4)
			return true;
		else
			return false;
	}
	public void Serialize(CArchive ar)//序列化三角形图元
	{  }
	public void SetValue(ElementType t, int x, int y, int w, int l)
	{
		Type = t; OrgX = x;OrgY = y; width = w;length = l;
	}	
	public void GetValue(ElementType t, int x, int y, int w, int l)
	{
		t = Type;x = OrgX;y = OrgY;w = width;l = length;
	}
			
}

class Circle extends Shape{
	int radius;//半径
	int width;
	int length;
	
	Circle(){}
	Circle(int x, int y, int r)
	{
		OrgX = x;//点坐标
		OrgY = y;
		radius = r;
		BorderColor = RGB(1, 1, 1);//边界颜色
		BorderType = 0;//边界线型--实线、虚线、虚点线等
		BorderWidth = 2;//边界宽度
		FillColor = RGB(255, 255, 255);//填充颜色
		FillType = 1;//填充类型--实心、双对角、十字交叉等
	}
	private Color RGB(int i, int j, int k) {
		// TODO 自动生成的方法存根
		return null;
	}
   public void Draw(CDC pDC)//绘制圆形
   {
	   
   }
   public Boolean IsMatched(CPoint pnt)//重载点pnt是否落在图元内
   {
	   if (((pnt.x - OrgX) * (pnt.x - OrgX)) + ((pnt.y - OrgY) * (pnt.y - OrgY)) <= (radius*radius))
			return true;
		else
			return false;
   }
   public void Serialize(CArchive ar)//序列化圆形图元
   {}
  public void SetValue(ElementType t, int x, int y, int w, int l)
  {
		Type = t; OrgX = x;OrgY = y; width = w;length = l;
	}
  public void GetValue(ElementType t, int x, int y, int w, int l)
  {
		t = Type;x = OrgX;y = OrgY;w = width;l = length;
  }
	
}

class  CEllipse extends Shape{
	private static final int HS_HORIZONTAL = 0;
	private static final int HS_DIAGCROSS = 0;
	int width;//长和宽
	int length;
	
	CEllipse(){
		
	}
	CEllipse(int x, int y, int w, int l){
		OrgX = x;//点坐标
		OrgY = y;
		width = w;
		length = l;
		BorderColor = RGB(1, 1, 1);//边界颜色
		BorderType = 0;//边界线型--实线、虚线、虚点线等
		BorderWidth = 1;//边界宽度
		FillColor = RGB(255, 255, 255);//填充颜色
		FillType = 1;//填充类型--实心、双对角、十字交叉等
	}
	private Color RGB(int i, int j, int k) {
		// TODO 自动生成的方法存根
		return null;
	}
	public void Draw(CDC pDC)//绘制椭圆形
	{
		CPen pen = null, pOldPen;//新、老画笔
		pen.CreatePen(BorderType, BorderWidth, BorderColor);//创建画笔（类型、颜色、宽度）
		pOldPen = (CPen)pDC.SelectObject(pen);//选择新画笔并保存老画笔
		CBrush brush = null, pOldBrush;//新老画刷
		if (FillType >= HS_HORIZONTAL && FillType <= HS_DIAGCROSS)
			brush.CreateHatchBrush(FillType, FillColor);//填充类型为用户自定义填充类型
		else
			brush.CreateSolidBrush(FillColor); //填充类型为默认白色填充类型
		pOldBrush = (CBrush)pDC.SelectObject(brush);
		pDC.Ellipse(OrgX - length, OrgY + width, OrgX + length, OrgY - width);//绘制椭圆
		pDC.SelectObject(pOldPen);//恢复老画笔、老画刷。
		pDC.SelectObject(pOldBrush);

	}
	public Boolean IsMatched(CPoint pnt)//重载点pnt是否落在图元内
	{
		if (((pnt.x - OrgX) * (pnt.x - OrgX)) + ((pnt.y - OrgY) * (pnt.y - OrgY)) <= (width*length))
			return true;
		else
			return false;
	}
	public void Serialize(CArchive ar)//序列化椭圆形图元
	{}

	public void SetValue(ElementType t, int x, int y, int w, int l)
	{
		Type = t; OrgX = x;OrgY = y; width = w;length = l;
	}
	public void GetValue(ElementType t, int x, int y, int w, int l)
	{
		t = Type;x = OrgX;y = OrgY;w = width;l = length;
	}
	
}


class CText extends Shape{
	private static final String ANSI_CHARSET = null;
	private static final String OUT_DEFAULT_PRECIS = null;
	private static final String CLIP_DEFAULT_PRECIS = null;
	private static final String DEFAULT_QUALITY = null;
	private static final int DEFAULT_PITCH = 0;
	private static final int FF_SWISS = 0;
	int height;//
	int angle;
	CString text;
	int width;
	int length;
	
	CText(){}
	CText(int x, int y, int h, int a, CString t)
	{
		OrgX = x;//点坐标
		OrgY = y;
		height = 30;
		angle = a;
		text = t;
		BorderColor = RGB(1, 1, 1);//边界颜色
		BorderType = 0;//边界线型--实线、虚线、虚点线等
		BorderWidth = 1;//边界宽度
		FillColor = RGB(255, 255, 255);//填充颜色
		FillType = 1;//填充类型--实心、双对角、十字交叉等
	}
	private Color RGB(int i, int j, int k) {
		// TODO 自动生成的方法存根
		return null;
	}
   public void Draw(CDC pDC)//绘制文本
   {
	   CFont  poldfont, pnewfont = null;
		//pnewfont = new CFont;
		pnewfont.CreateFont(
			height,						//字逻辑高度
			0,							//字逻辑宽度
			angle * 10,						//出口矢量与X轴夹角
			angle * 10,						//基线与X轴轴夹角
			1,							//字体磅值
			0,							//是否斜体
			0,							//是否带下划线
			0,							//是否有删除线
			ANSI_CHARSET,				//字符集
			OUT_DEFAULT_PRECIS,         // 裁剪精度
			CLIP_DEFAULT_PRECIS,        // 输出质量
			DEFAULT_QUALITY,            // 调距和字体族
			DEFAULT_PITCH | FF_SWISS,   // 字体的字样名字
			_T("Times New Roman")		//字体名称
		);
		poldfont = pDC.SelectObject(pnewfont);// //在将新对象选进设备环境的同时返回指向前一次被选对象的指针。作用保存原来的对象，以便完成任务时恢复它。

		pDC.SetTextColor(BorderColor);
		pDC.SetBkColor(FillColor);
		pDC.TextOut(OrgX, OrgY, text);

		pDC.SelectObject(poldfont);//恢复老画笔
		//delete pnewfont1;
		
	   
   }
	private Object _T(String string) {
	// TODO 自动生成的方法存根
	return null;
   }
	public Boolean IsMatched(CPoint pnt)//重载点pnt是否落在图元内
	{
		if ((pnt.x >= OrgX - height) && (pnt.x <= OrgX + height) && (pnt.y >= OrgY - height) && (pnt.y <= OrgY + height))
			return true;
		else
			return false;
	}
	public void Serialize(CArchive ar)//序列化文本图元
	{   }
	
	public void SetValue(ElementType t, int x, int y, int w, int l)
	{
		Type = t; OrgX = x;OrgY = y; width = w;length = l;
	}
	void GetValueGetValue(ElementType t, int x, int y, int w, int l)
	{
		t = Type;x = OrgX;y = OrgY;w = width;l = length;

	}
	public void SetText(int m_text)
	{ }
	public void GetText(int m_text)
	{}
		
}
