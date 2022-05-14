
//여러 사람이 프로젝트에 참여하게되면 중복된 함수 이름이 자주 발생한다.
//브라우저의 스코프는 공용공간으로 쓰이기 때문에 나중에 로딩된 중복 이름 함수가 먼저 로딩된 함수를 덮어쓰게 됨
//이런 문제를 피하기 위해 이렇게 index.js만의 유효범위를 만들어 사용
var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        //jQuery에서 제공하는 ajax를 사용하여 간단하게 서버와 데이터 통신 가능
        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            //글 등록이 성공하면 메인페이지로 돌아간다.
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            //PostsApiController에 이미 @PunMapping으로 선언했기 때문에 PUT사용
            //생성-POST, 읽기-GET, 수정-PUT. 삭제-DELETE
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();