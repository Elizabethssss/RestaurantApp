const signUpSlideBtn = document.getElementById('signUpSlideBtn');
const loginSlideBtn = document.getElementById('loginSlideBtn');
const container = document.getElementById('container');

signUpSlideBtn.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});

loginSlideBtn.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});


