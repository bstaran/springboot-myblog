function isValidContents(contents) {
    if (contents === '') {
        alert('내용을 입력해주세요');
        return false;
    }
    return true;
}

function editPost(id) {
    showEdits(id);
    let title = $(`#${id}-title`).text();
    let contents = $(`#${id}-contents`).text().trim();
    $(`#${id}-title-textarea`).val(title);
    $(`#${id}-textarea`).val(contents);
}

function showEdits(id) {
    $(`#${id}-editarea`).show();
    $(`#${id}-title-editarea`).show();
    $(`#${id}-submit`).show();
    $(`#${id}-delete`).show();

    $(`#${id}-title`).hide();
    $(`#${id}-contents`).hide();
    $(`#${id}-edit`).hide();
}

function hideEdits(id) {
    $(`#${id}-editarea`).hide();
    $(`#${id}-title-editarea`).hide();
    $(`#${id}-submit`).hide();
    $(`#${id}-delete`).hide();

    $(`#${id}-title`).show();
    $(`#${id}-contents`).show();
    $(`#${id}-edit`).show();
}

$(document).ready(function () {
    getMessages();
})

function getMessages() {
    $('#cards-box').empty();
    $.ajax({
        type: "GET",
        url: "api/postings",
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                let posting = response[i];
                let id = posting.id;
                let username = posting.username;
                let title = posting.title;
                let contents = posting.contents;
                let modifiedAt = posting.modifiedAt;

                addHTML(id, username, title, contents, modifiedAt)
            }
        },
    })
}

function addHTML(id, username, title, contents, modifiedAt) {
    let tempHTML = `<div class="card">
                        <!-- date/username 영역 -->
                        <div class="metadata">
                            <div class="date">
                                ${modifiedAt}
                            </div>
                            <div id="${id}-username" class="username">
                                ${username}
                            </div>
                        </div>
                        <!-- contents 조회/수정 영역-->
                        <div class="contents">
                            <h3 style="margin-top: 10px" id="${id}-title" class="text">${title}</h3>
                            <div id="${id}-title-editarea" class="edit">
                                <textarea style="margin-top: 10px" id="${id}-title-textarea" class="te-edit" cols="30" rows="1" placeholder="수정할 제목을 입력해주세요."></textarea>
                                <input id="${id}-pw" type="password" placeholder="사용했던 비밀번호를 입력해주세요.">
                            </div>
                            
                            <div id="${id}-contents" class="text">
                                ${contents}
                            </div>
                            <div id="${id}-editarea" class="edit">
                                <textarea id="${id}-textarea" class="te-edit" name="" id="" cols="30" rows="5" placeholder="수정할 내용을 입력해주세요."></textarea>
                            </div>
                        </div>
                        <!-- 버튼 영역-->
                        <div class="footer">
                            <button type="button" id="${id}-edit" onclick="editPost('${id}')" class="btn btn-ligth">수정</button>
                            <button type="button" id="${id}-delete" onclick="deleteOne('${id}')" class="btn btn-ligth">삭제</button>
                            <button type="button" id="${id}-submit" onclick="submitEdit('${id}')" class="btn btn-ligth">확인</button>
                        </div>
                    </div>`
    $('#cards-box').append(tempHTML);
}

// 메모를 생성합니다.
function writePost() {
    let username = $('#user').val();
    let pw = $('#pw').val();
    let title = $('#title').val();
    let contents = $('#contents').val();
    if(isValidContents(contents)===false) {
        return;
    }
    let data = {
        'username':username,
        'pw':pw,
        'title':title,
        'contents':contents
    }
    $.ajax({
        type:"POST",
        url:"api/postings",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert("성공적으로 작성되었습니다.");
            window.location.reload();
        },
    })
}

// 메모를 수정합니다.
function submitEdit(id) {
    let username = $(`#${id}-username`).text().trim();
    let title = $(`#${id}-title-textarea`).val().trim();
    let contents = $(`#${id}-textarea`).val().trim();
    let pw = $(`#${id}-pw`).val()
    if (!(isValidContents(contents))) return;
    let data = {
        'username':username,
        'title':title,
        'contents':contents,
        'pw':pw
    }
    $.ajax({
        type: "PUT",
        url:`/api/postings/${id}/${pw}`,
        data:JSON.stringify(data),
        contentType: 'application/json',
        success: function (response) {
            if (response===0) alert("비밀번호를 확인해주세요.");
            else alert("변경되었습니다.");
            window.location.reload();
        }
    })
}

// 메모를 삭제합니다.
function deleteOne(id) {
    let pw = prompt("비밀번호를 입력해주세요.");
    $.ajax({
        type: "DELETE",
        url: `/api/postings/${id}/${pw}`,
        success: function (response) {
            if (response===0) alert("비밀번호를 확인해주세요.");
            else alert("변경되었습니다.");
            window.location.reload();
        }
    })
}