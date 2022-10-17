alert(JSON.stringify(document));


const f = () => {
	window.location.href = 'https://google.com';
};


setTimeout(f, 5 * 1000);



function setTimeout (func, timeoutMs){
	
	//wait timeoutMs
	
	//....
	func()
	
}

//console.log(window);

//console.log(document)