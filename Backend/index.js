var http = require( 'http' ),
    fs   = require( 'fs' ),
    path = require( 'path' ),
    Url  = require( 'url' ),
    datas= require( '../Database/database.json' );
http.createServer( creatHttpServer ).listen( 4239, '127.0.0.1' );

console.log( ' - server start at port: 4239' );

function creatHttpServer( request, responder ){
    //请求参数集
    var params = Url.parse( request.url, true )[ 'query' ],
        type   = params.type,
        resData;
    if( 'hotplace' == type ){
        resData = getHotData( params );
    } else if( 'placedetail' == type ){
        resData = getDetailData( params );
    } else if( 'city' == type ){
        resData = getCityData( params );
    }
    responder.writeHead( 200, { 'Content-Type': 'text/html' });
    responder.end( JSON.stringify( resData ) );
}

function getHotData( params ){
    var count   = params.count,
        resData = []; 
    ( !count || 'A' == count ) && ( count = 20 );
    //FIXME:默认最多返回20条数据，目前测试只有一条数据，所以先默认都为1条数据。
    count = 1;
    return {
        result : datas.hotplace.slice( 0, count )
    }
}

function getDetailData( params ){
    var placeId = params[ 'placeid' ];
    return {
        result : datas[ 'places' ][ placeId ]
    };
}

function getCityData( params ){
    //FIXME:目前没有考虑分页
    //FIXME:目前仅支持二级省市
    var count  = params.count,
        cityid = params.cityid,
        page   = params.page,
        placesData = datas[ 'places' ], 
        placesArr  = cityid.split( '_' ),
        places = Object.keys( datas[ 'citys' ][ placesArr[ 0 ] ][ placesArr[ 1 ] ] ),
        resData= [];
    debugger;
    for( var i in places ){
        resData.push( placesData[ places[ i ] ] );
    }
    return {
        result : resData,
        page   : page,
        count  : count
    };
}