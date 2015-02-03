//小学升初中
function createprintpage_xsc(name,no){
	
}
//初中升高中
function createprintpage_csg(name,no){
	LODOP.PRINT_INITA("0cm","0cm","19cm","12.01cm","准考证打印");
	LODOP.SET_PRINT_PAGESIZE(1,1950,1250,"");
	LODOP.SET_PRINT_MODE("DOUBLE_SIDED_PRINT",true);//双面打印
	LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW",1);//打印后自动关闭预览窗口
	LODOP.SET_PREVIEW_WINDOW(0,1,1,800,600,"准考证预览.打印准考证");
	LODOP.SET_SHOW_MODE("BKIMG_IN_PREVIEW",true);//打印预览时是否包含背景图
	LODOP.SET_SHOW_MODE("BKIMG_PRINT",true);//是否打印背景
	
	LODOP.NewPage();
	//LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='http://localhost/yx/res/printImg/csgz.jpg'>");
	//LODOP.SET_SHOW_MODE("BKIMG_WIDTH",718);//背景宽
	//LODOP.SET_SHOW_MODE("BKIMG_HEIGHT",454);
	LODOP.ADD_PRINT_IMAGE(1,2,"190mm","120.1mm","<img border='0' src='http://localhost/yx/res/print/csgz.jpg'>");
	LODOP.SET_PRINT_STYLEA(0,"Stretch",2);
	LODOP.ADD_PRINT_TEXT("9.18cm","9cm","2.8cm","0.85cm",name);
	LODOP.SET_PRINT_STYLEA(0,"FontName","华文新魏");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
	LODOP.SET_PRINT_STYLEA(0,"AlignJustify",1);
	LODOP.SET_PRINT_STYLEA(0,"PageIndex","first");
	LODOP.ADD_PRINT_TEXT("7.96cm","2.7cm","2.8cm","0.85cm",name);
	LODOP.SET_PRINT_STYLEA(0,"FontName","华文新魏");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",17);
	LODOP.SET_PRINT_STYLEA(0,"PageIndex","first");
	LODOP.ADD_PRINT_BARCODE("8.73cm","13.1cm","4.95cm","1.98cm","128A",no);
	LODOP.SET_PRINT_STYLEA(0,"GroundColor","#FFFFFF");
	LODOP.SET_PRINT_STYLEA(0,"PageIndex","first");
	LODOP.ADD_PRINT_BARCODE("9.29cm","1.01cm","4.87cm","1.85cm","128A",no);
	LODOP.SET_PRINT_STYLEA(0,"GroundColor","#FFFFFF");
	LODOP.SET_PRINT_STYLEA(0,"PageIndex","first");
	LODOP.SET_PRINT_STYLEA(0,"Stretch",1);
	
	LODOP.NewPage();
	//LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='http://localhost/yx/res/printImg/csgb.jpg'>");
	//LODOP.SET_SHOW_MODE("BKIMG_WIDTH",718);//背景宽
	//LODOP.SET_SHOW_MODE("BKIMG_HEIGHT",454);
	LODOP.ADD_PRINT_IMAGE(1,2,"190mm","120.1mm","<img border='0' src='http://localhost/yx/res/print/csgb.jpg'>");
	LODOP.SET_PRINT_STYLEA(0,"Stretch",2);
}