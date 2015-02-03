<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GCxGwN5A8pWBYDCMoCW0oGMb"></script>
<title>百度地图的Hello, World</title>
<style type="text/css">
body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;}
</style>
</head>
<body>
<div id="r-result">
    test:<br /><input type="text" id="suggestId" size="20" value="test" style="width:150px;" /></div>
    <div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto;">
</div>
<div id="allmap"></div>
</body>
<script type="text/javascript">
// 渝高公园
var x = 106.494918;
var y = 29.534739; 

var map = new BMap.Map("allmap",{minZoom:4,maxZoom:18});            
//var point = new BMap.Point(116.404, 39.915);    
//map.centerAndZoom(point,15);                     
map.centerAndZoom("重庆",14);                     
map.enableScrollWheelZoom();                     

map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
map.addControl(new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}));  //右上角，仅包含平移和缩放按钮
map.addControl(new BMap.NavigationControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT, type: BMAP_NAVIGATION_CONTROL_PAN}));  //左下角，仅包含平移按钮
map.addControl(new BMap.NavigationControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT, type: BMAP_NAVIGATION_CONTROL_ZOOM}));  //右下角，仅包含缩放按钮

//创建小狐狸
var pt = new BMap.Point(106.499733, 29.532162);
var marker2 = new BMap.Marker(pt);  // 创建标注
marker2.setAnimation(BMAP_ANIMATION_BOUNCE);
map.addOverlay(marker2);              // 将标注添加到地图中

//让小狐狸说话（创建信息窗口）
var infoWindow2 = new BMap.InfoWindow("<p style='font-size:14px;'>哈哈！</p>");
marker2.addEventListener("click", function(){this.openInfoWindow(infoWindow2);});


map.addEventListener("click", function(e){
	console.warn(e.point.lng + ", " + e.point.lat);
    var pt = e.point;
    new BMap.Geocoder().getLocation(pt, function(rs){
        var addComp = rs.addressComponents;
        alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
    }); 
	
	});
	

function G(id) {
    return document.getElementById(id);
}	
var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
	    {"input" : "suggestId"
	    ,"location" : map
	});
	
ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
	var str = "";
	    var _value = e.fromitem.value;
	    var value = "";
	    if (e.fromitem.index > -1) {
	        value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
	    }    
	    str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
	    
	    value = "";
	    if (e.toitem.index > -1) {
	        _value = e.toitem.value;
	        value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
	    }    
	    str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
	    G("searchResultPanel").innerHTML = str;
	});
	
var myValue;
ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
var _value = e.item.value;
    myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
    G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
    setPlace();
});

function setPlace(){
    map.clearOverlays();    //清除地图上所有覆盖物
    function myFun(){
        var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
        map.centerAndZoom(pp, 18);
        map.addOverlay(new BMap.Marker(pp));    //添加标注
    }
    var local = new BMap.LocalSearch(map, { //智能搜索
      onSearchComplete: myFun
    });
    local.search(myValue);
}
</script>






