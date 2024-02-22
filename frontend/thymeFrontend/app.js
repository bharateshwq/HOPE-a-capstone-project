const hamburger = document.querySelector('.header .nav-bar .nav-list .hamburger');
const mobile_menu = document.querySelector('.header .nav-bar .nav-list ul');
const menu_item = document.querySelectorAll('.header .nav-bar .nav-list ul li a');
const header = document.querySelector('.header.container');

hamburger.addEventListener('click', () => {
	hamburger.classList.toggle('active');
	mobile_menu.classList.toggle('active');
});

document.addEventListener('scroll', () => {
	var scroll_position = window.scrollY;
	if (scroll_position > 250) {
		header.style.backgroundColor = '#29323c';
	} else {
		header.style.backgroundColor = 'transparent';
	}
});

menu_item.forEach((item) => {
	item.addEventListener('click', () => {
		hamburger.classList.toggle('active');
		mobile_menu.classList.toggle('active');
	});
});
function redirectToLoginPage() {
	location.href = "Login.html";
}

var images = ['./img/background1.png','./img/background2.jpg','./img/background3.png','./img/background5.png']; // List of image filenames
var currentIndex = 0; 

// Function to change the image
function changeImage() {
	var slideshowElement = document.createElement('img');
	slideshowElement.src = images[currentIndex];
	slideshowElement.alt = 'Slideshow Image';
	slideshowElement.id = 'slideshow';
	var container = document.getElementById('imageContainer');
	container.innerHTML = ''; // Clear existing image
	container.appendChild(slideshowElement);
	currentIndex = (currentIndex + 1) % images.length; // Move to the next image, loop back to the beginning if at the end
}

// Initial image change
changeImage();

// Change image every 3 seconds (3000 milliseconds)
setInterval(changeImage, 3000);