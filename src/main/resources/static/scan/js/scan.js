var recoURL='https://192.168.4.147:5000/face';


var video = null;
var canvas = document.createElement('canvas');
var photo = document.createElement('img');
var capTimer = null;
var width = 300;
var height = 400;
var data = null;
var winWidth = window.screen.width;
var winHeight = window.screen.height;
var scanFrame = document.getElementsByClassName('container-frame')[0];
var count = 1;
var scanCount = 0;

var frameWidth = scanFrame.offsetWidth;

function getVideo() {
    if (!navigator.getUserMedia)
        navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia ||
            navigator.mozGetUserMedia || navigator.msGetUserMedia;


    if (!navigator.mediaDevices || !navigator.mediaDevices.enumerateDevices) {
        console.log("enumerateDevices() not supported.");
        return;
    }

    var exArray = []; 
    navigator.mediaDevices.enumerateDevices()
        .then(function (devices) {
            devices.forEach(function (device) {
                if (device.kind === 'videoinput') {
                    exArray.push(device.deviceId);
                }
            });
            if (navigator.getUserMedia) {
                navigator.getUserMedia({video: {deviceId: exArray[1]}}, captureVideo, function (e) {
                    alert('Error capturing audio.');
                });
            } else {
                alert('getUserMedia not supported in this browser.');
            }
        })
        .catch(function (err) {
            console.log(err.name + ": " + err.message);
        });

}

function captureVideo(stream) {
    video = document.getElementById('video');
    width = window.screen.width;
    height = window.screen.height;
    video.setAttribute('width', width);
    video.setAttribute('height', height);


    if (window.URL) {
        video.src = window.URL.createObjectURL(stream);
    } else {
        video.src = stream;
    }

    video.autoplay = true;
}


function takePicture() {
 var size=video.videoHeight;
    canvas.width = size;
    canvas.height = size;    
var context = canvas.getContext('2d');
    if (width && height) {
        var sx = Math.floor((video.videoWidth - size) / 2);
        var sy = 0;
        var w = size;
        var h = size;
        context.drawImage(video, sx, sy, w, h, 0, 0, w, h);
        var data2 = canvas.toDataURL('image/jpeg');

        if (data === data2) {
        } else {
            data = data2;
            $.ajax({
                type: 'POST',
                contentType: false,
                url: recoURL,
                data: data,
                error: function (err) {
                   console.log(err)
                },
                success: scanSuccess
            });
        }
    } else {
        clearPhoto();
    }
}

function scanSuccess(mes) {
    //if(count === 1){
    // alert(mes);
    // return;
    console.log(mes)
        var d = mes.split(":")
        if (d.length === 1) {
            //alert(mes);
            return;
        }
        var remoteFaceid = d[1].trim()
        //?uid=119&uname=111123w&ufaceid=4869726816
        var qs = {};
        var query = location.search.slice(1).split('&').forEach(item => {
            var [key, value] = item.split('=');
            qs[key] = value;
        })
        console.log(qs);
        //var uid = location.search.split("=")[1]
        //var uname = location.search.split("=")[2]
        //var faceid=location.search.split("=")[3]
        //console.log('url: faced', faceid)
        //console.log('data', mes, mes === faceid)

        if(qs.ufaceid===remoteFaceid)
        {
            alert("欢迎"+qs.uname+"登录!")
            window.location.href="http://192.168.5.108:3000/user/home/getPlatform"
        }else
        {
            scanCount++;
            
            if (scanCount >= 3) {
                alert("您已三次没有通过人脸识别，请您重新登录");
                window.location.href="http://192.168.5.108:3000/user/login"
            } else {
                alert("抱歉，您没有通过人脸识别，请重试");
            }
            //window.location.href="http://192.168.5.118:3000/user/login"

        }
        // let url = `http://192.168.5.118:3000/user/login/checkFaceId?id=${uid}&face_id=${ mes }`
        // let img = new Image();

        // img.onerror = function (error) {
        //     console.log(error);
        // }

        // if(img.complete){
        //     alert("欢迎"+uname+"登录")
        //     window.location.href="http://192.168.40.98/user/home/getPlatform"
        // }else{
        //     img.onload = function (event) {
        //         console.log("//419651561651");
        //         console.log('ewew', face_id);
        //         console.log('onload', event);
        //         alert("欢迎登录")
        //         window.location.href="http://192.168.40.98/user/home/getPlatform"
        //     }
        // }
        // img.src = url;
        
    //    count ++
    //}

}

function clearPhoto() {
    var context = canvas.getContext('2d');
    context.fillStyle = "#AAA";
    context.fillRect(0, 0, canvas.width, canvas.height);
}

function scanLineMove() {
    var height = document.getElementsByClassName('container-frame')[0].offsetHeight;
    height = height - 20;
    $('#scan-line').css("top", "0px");
    $('#scan-line').animate({top: height}, 2000, "linear", scanLineMove);
}

function startScan() {
    $('#scan-btn').hide();
    $('#scan-line').show();
    scanLineMove();
    setTimeout(function () {
        capTimer = setInterval(takePicture, 2000);
        clearPhoto();
    }, 3000);
}

function doPostBack(url, backFunc, queryParam, errFunc) {
    $.ajax({
        type: 'POST',
        contentType: 'application/json; charset=UTF-8',
        dataType: 'json', 
        url: url,
        data: JSON.stringify(queryParam),
        error: errFunc,
        success: backFunc
    });
}


function intoScan(id) {
    $('#scan-line').css("top", "0px");
    jump('scan', id);
    jump('scan-btn');
    if (id == 'index') {
        getVideo();
        if (firstInto == 'yes') {
            setTimeout(function () {
                jump('strategy');
                firstInto = 'no';
            }, 500);
        }
    }
    $("#animate").children('div').css('display', 'none');
    $('#animate').hide();
    $('#control_tip').hide();
}


getVideo();



