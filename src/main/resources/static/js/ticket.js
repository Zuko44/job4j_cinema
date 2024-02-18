let elems = document.querySelectorAll(".place-color");
let button1 = document.querySelector(".input-row");
let button2 = document.querySelector(".input-place");

let shows = document.querySelectorAll('.show-place');
let row = document.querySelector('.row-count');
let place = document.querySelector('.place-count');

for (let elem of elems) {
	elem.addEventListener('click', function() {
	for (let show of shows) {
	show.classList.remove('show-place');
    	show.classList.add('show-show-place');
	}

	row.textContent = elem.dataset.row;
	place.textContent = elem.dataset.place;
	button1.value = "";
	button2.value = "";
    button1.value = elem.dataset.row;
    button2.value = elem.dataset.place;
    });
}