alert('dd')

$.ajax({
  url: '/board/getboards',
  type: 'get',
  success: function(re){
    console.log(re)
  }
})