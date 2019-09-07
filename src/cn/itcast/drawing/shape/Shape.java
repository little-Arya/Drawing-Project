package cn.itcast.drawing.shape;

import java.lang.annotation.ElementType;

import com.sun.prism.paint.Color;

public class Shape {
	   protected ElementType Type;//ͼԪ����
	   protected int OrgX;//ԭ������
	   protected int OrgY;
	   protected Color   BorderColor;//�߽���ɫ
	   protected int BorderType;//�߽�����--ʵ�ߡ����ߡ�����ߵ�
	   protected int BorderWidth;//�߽���
	   protected Color  FillColor;//�����ɫ
	   protected int FillType;//�������--ʵ�ġ�˫�Խǡ�ʮ�ֽ����
  Shape(){}
  public void Draw(CDC pDC)
  {
	  pDC=null;
  }
  //����ͼԪ
  public Boolean IsMatched(CPoint pnt) {
	return null;
  }//���Ƿ�����ͼ���ڲ�
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
	public void GetValue(ElementType t, int x, int y, int width, int height) //��ȡ���ݳ�Ա��ֵ
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
		OrgX = x;//������
		OrgY = y;
		width = w;
		BorderColor = RGB(255, 0, 0);//�߽���ɫ
		BorderType = 0;//�߽�����--ʵ�ߡ����ߡ�����ߵ�
		BorderWidth = 25;//�߽���
		FillColor = RGB(0, 0, 255);//�����ɫ
		FillType = 1;//�������--ʵ�ġ�˫�Խǡ�ʮ�ֽ����
	}
	private Color RGB(int i, int j, int k) {
		// TODO �Զ����ɵķ������
		return null;
	}
	
	public void Draw(CDC pDC)//����������
	{
		CPen pen = null, pOldPen;//�¡��ϻ���
		pen.CreatePen(BorderType, BorderWidth, BorderColor);//�������ʣ����͡���ɫ����ȣ�
		pOldPen = (CPen)pDC.SelectObject(pen);//ѡ���»��ʲ������ϻ���
		CBrush brush = null, pOldBrush;//���ϻ�ˢ
		if (FillType >= HS_HORIZONTAL && FillType <= HS_DIAGCROSS)
			brush.CreateHatchBrush(FillType, FillColor);//�������Ϊ�û��Զ����������
		else
			brush.CreateSolidBrush(FillColor); //�������ΪĬ�ϰ�ɫ�������
		pOldBrush = (CBrush)pDC.SelectObject(brush);

		pDC.Rectangle(OrgX - width / 2, OrgY - width / 2, OrgX + width / 2, OrgY + width / 2);//����������

		pDC.SelectObject(pOldPen);//�ָ��ϻ��ʡ��ϻ�ˢ��
		pDC.SelectObject(pOldBrush);
	}
	
    public Boolean IsMatched(CPoint pnt)
    {
    	if ((pnt.x >= OrgX - width / 2) &&(pnt.x <= OrgX + width / 2) && (pnt.y >= OrgY - width / 2) && (pnt.y <= OrgY + width / 2))
    		return true;
    	else
    		return false;
    }
	public void Serialize(CArchive ar)//���л�������ͼԪ
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
	Color   BorderColor;//�߽���ɫ
	int BorderType;//�߽�����--ʵ�ߡ����ߡ�����ߵ�
	int BorderWidth;//�߽���
	Color  FillColor;//�����ɫ
	int FillType;//�������--ʵ�ġ�˫�Խǡ�ʮ�ֽ���

	CRectangle(){}
	CRectangle(int x, int y, int w, int h){
	Type = RECTANGLE;
	OrgX = x;//ԭ������
	OrgY = y;
	BorderColor = RGB(255, 0, 0);//�߽���ɫ
	BorderType = PS_SOLID;//�߽�����--ʵ�ߡ����ߡ�����ߵ�
	BorderWidth = 1;//�߽���
	FillColor = RGB(0, 255, 0);//�����ɫ
	FillType = HS_HORIZONTAL;//�������--ʵ�ġ�˫�Խǡ�ʮ�ֽ����
	width = w; height = h;
	}
	private Color RGB(int i, int j, int k) {
		// TODO �Զ����ɵķ������
		return null;
	}
	
	public void Draw(CDC pDC)//����������
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
	public Boolean IsMatched(CPoint pnt)//���ص�pnt�Ƿ�����ͼԪ��
	{
		if ((pnt.x >= OrgX - width / 2) && (pnt.x <= OrgX + width / 2) && (pnt.y >= OrgY - height / 2) && (pnt.y <= OrgY + height / 2))
			return true;
		else
			return false;

	}
	public void Serialize(CArchive ar)//���л�������ͼԪ
	{
		
	}
	public void SetValue(ElementType t, int x, int y, int width, int height)//���ó����ζ����еĲ������ݳ�Ա���뻭�ʺͻ�ˢ�޹صģ���ֵ
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
		OrgX = x;//������
		OrgY = y;
		width = w;
		BorderColor = RGB(1, 1, 1);//�߽���ɫ
		BorderType = 0;//�߽�����--ʵ�ߡ����ߡ�����ߵ�
		BorderWidth = 2;//�߽���
		FillColor = RGB(255, 255, 255);//�����ɫ
		FillType = 1;//�������--ʵ�ġ�˫�Խǡ�ʮ�ֽ����
	}
	private Color RGB(int i, int j, int k) {
		// TODO �Զ����ɵķ������
		return null;
	}
	public void Draw(CDC pDC)//����������
	{
		CPen pen = null, pOldPen;//�¡��ϻ���
		pen.CreatePen(BorderType, BorderWidth, BorderColor);//�������ʣ����͡���ɫ����ȣ�
		pOldPen = (CPen)pDC.SelectObject(pen);//ѡ���»��ʲ������ϻ���
		CBrush brush = null, pOldBrush;//���ϻ�ˢ
		if (FillType >= HS_HORIZONTAL && FillType <= HS_DIAGCROSS)
			brush.CreateHatchBrush(FillType, FillColor);//�������Ϊ�û��Զ����������
		else
			brush.CreateSolidBrush(FillColor); //�������ΪĬ�ϰ�ɫ�������
		pOldBrush = (CBrush)pDC.SelectObject(brush);

		int x1 = OrgX - width * 58 / 100;				//�����ε�������
		int y1 = OrgY + width / 3;
		int x2 = OrgX + 58 * width / 100;
		int y2 = OrgY + width / 3;
		int x3 = OrgX;
		int y3 = OrgY - width * 2 / 3;
		CPoint a[] = null ;
		pDC.Polygon(a, 3);
		pDC.SelectObject(pOldPen);//�ָ��ϻ��ʡ��ϻ�ˢ��
		pDC.SelectObject(pOldBrush);

	}
	
	public Boolean IsMatched(CPoint pnt)//���ص�pnt�Ƿ�����ͼԪ��
	{
		int x1 = OrgX - width * 58 / 100;				//�����ε�������
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
	public void Serialize(CArchive ar)//���л�������ͼԪ
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
	int radius;//�뾶
	int width;
	int length;
	
	Circle(){}
	Circle(int x, int y, int r)
	{
		OrgX = x;//������
		OrgY = y;
		radius = r;
		BorderColor = RGB(1, 1, 1);//�߽���ɫ
		BorderType = 0;//�߽�����--ʵ�ߡ����ߡ�����ߵ�
		BorderWidth = 2;//�߽���
		FillColor = RGB(255, 255, 255);//�����ɫ
		FillType = 1;//�������--ʵ�ġ�˫�Խǡ�ʮ�ֽ����
	}
	private Color RGB(int i, int j, int k) {
		// TODO �Զ����ɵķ������
		return null;
	}
   public void Draw(CDC pDC)//����Բ��
   {
	   
   }
   public Boolean IsMatched(CPoint pnt)//���ص�pnt�Ƿ�����ͼԪ��
   {
	   if (((pnt.x - OrgX) * (pnt.x - OrgX)) + ((pnt.y - OrgY) * (pnt.y - OrgY)) <= (radius*radius))
			return true;
		else
			return false;
   }
   public void Serialize(CArchive ar)//���л�Բ��ͼԪ
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
	int width;//���Ϳ�
	int length;
	
	CEllipse(){
		
	}
	CEllipse(int x, int y, int w, int l){
		OrgX = x;//������
		OrgY = y;
		width = w;
		length = l;
		BorderColor = RGB(1, 1, 1);//�߽���ɫ
		BorderType = 0;//�߽�����--ʵ�ߡ����ߡ�����ߵ�
		BorderWidth = 1;//�߽���
		FillColor = RGB(255, 255, 255);//�����ɫ
		FillType = 1;//�������--ʵ�ġ�˫�Խǡ�ʮ�ֽ����
	}
	private Color RGB(int i, int j, int k) {
		// TODO �Զ����ɵķ������
		return null;
	}
	public void Draw(CDC pDC)//������Բ��
	{
		CPen pen = null, pOldPen;//�¡��ϻ���
		pen.CreatePen(BorderType, BorderWidth, BorderColor);//�������ʣ����͡���ɫ����ȣ�
		pOldPen = (CPen)pDC.SelectObject(pen);//ѡ���»��ʲ������ϻ���
		CBrush brush = null, pOldBrush;//���ϻ�ˢ
		if (FillType >= HS_HORIZONTAL && FillType <= HS_DIAGCROSS)
			brush.CreateHatchBrush(FillType, FillColor);//�������Ϊ�û��Զ����������
		else
			brush.CreateSolidBrush(FillColor); //�������ΪĬ�ϰ�ɫ�������
		pOldBrush = (CBrush)pDC.SelectObject(brush);
		pDC.Ellipse(OrgX - length, OrgY + width, OrgX + length, OrgY - width);//������Բ
		pDC.SelectObject(pOldPen);//�ָ��ϻ��ʡ��ϻ�ˢ��
		pDC.SelectObject(pOldBrush);

	}
	public Boolean IsMatched(CPoint pnt)//���ص�pnt�Ƿ�����ͼԪ��
	{
		if (((pnt.x - OrgX) * (pnt.x - OrgX)) + ((pnt.y - OrgY) * (pnt.y - OrgY)) <= (width*length))
			return true;
		else
			return false;
	}
	public void Serialize(CArchive ar)//���л���Բ��ͼԪ
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
		OrgX = x;//������
		OrgY = y;
		height = 30;
		angle = a;
		text = t;
		BorderColor = RGB(1, 1, 1);//�߽���ɫ
		BorderType = 0;//�߽�����--ʵ�ߡ����ߡ�����ߵ�
		BorderWidth = 1;//�߽���
		FillColor = RGB(255, 255, 255);//�����ɫ
		FillType = 1;//�������--ʵ�ġ�˫�Խǡ�ʮ�ֽ����
	}
	private Color RGB(int i, int j, int k) {
		// TODO �Զ����ɵķ������
		return null;
	}
   public void Draw(CDC pDC)//�����ı�
   {
	   CFont  poldfont, pnewfont = null;
		//pnewfont = new CFont;
		pnewfont.CreateFont(
			height,						//���߼��߶�
			0,							//���߼����
			angle * 10,						//����ʸ����X��н�
			angle * 10,						//������X����н�
			1,							//�����ֵ
			0,							//�Ƿ�б��
			0,							//�Ƿ���»���
			0,							//�Ƿ���ɾ����
			ANSI_CHARSET,				//�ַ���
			OUT_DEFAULT_PRECIS,         // �ü�����
			CLIP_DEFAULT_PRECIS,        // �������
			DEFAULT_QUALITY,            // �����������
			DEFAULT_PITCH | FF_SWISS,   // �������������
			_T("Times New Roman")		//��������
		);
		poldfont = pDC.SelectObject(pnewfont);// //�ڽ��¶���ѡ���豸������ͬʱ����ָ��ǰһ�α�ѡ�����ָ�롣���ñ���ԭ���Ķ����Ա��������ʱ�ָ�����

		pDC.SetTextColor(BorderColor);
		pDC.SetBkColor(FillColor);
		pDC.TextOut(OrgX, OrgY, text);

		pDC.SelectObject(poldfont);//�ָ��ϻ���
		//delete pnewfont1;
		
	   
   }
	private Object _T(String string) {
	// TODO �Զ����ɵķ������
	return null;
   }
	public Boolean IsMatched(CPoint pnt)//���ص�pnt�Ƿ�����ͼԪ��
	{
		if ((pnt.x >= OrgX - height) && (pnt.x <= OrgX + height) && (pnt.y >= OrgY - height) && (pnt.y <= OrgY + height))
			return true;
		else
			return false;
	}
	public void Serialize(CArchive ar)//���л��ı�ͼԪ
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
