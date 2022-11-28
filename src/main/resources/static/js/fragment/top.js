function potatoChat()
{
	const option='top=100,right=10,width=600,height=700,resizable=0,';
	const url='/potatoChat/pop';
	window.open(url, 'popup', option);
}

function searchSubmit()
{
	const searchForm=document.querySelector('.searchForm');
	const searchKeyword=document.querySelector('.searchInput').value;
	if(searchKeyword.charAt(0)==' ')
	{
		alert('잘못된 입력입니다.')
		return
	}
	searchForm.submit();
}