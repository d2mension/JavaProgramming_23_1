function favorites(e){
    var i = $(".Unfavorites").index(e); // 같은 클래스 내 index 값을 가져옴
    document.getElementsByClassName('Unfavorites')[i].style.display = "none"; // 즐겨찾기 취소 버튼 비활성화
    document.getElementsByClassName('Favorites')[i].style.display = "inline"; // 즐겨찾기 추가 버튼 활성화
 }
 // 즐겨찾기 해제
 function Unfavorites(e){
    var i = $(".Favorites").index(e); // 같은 클래스 내 index 값을 가져옴
    document.getElementsByClassName('Unfavorites')[i].style.display = "inline"; // 즐겨찾기 취소 버튼 비활성화
    document.getElementsByClassName('Favorites')[i].style.display = "none"; // 즐겨찾기 추가 버튼 활성화
 }