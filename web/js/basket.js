const basketItems = document.querySelectorAll('.basket-item button');
basketItems.forEach(item => {
    item.addEventListener('click', e => {
        let basketTarget;
        const dishAction = e.target.getAttribute('data-dish-action');
        switch (dishAction) {
            case 'remove':
                if (confirm('Do you want to delete this dish?')) {
                    basketTarget = e.target.parentElement;
                }
                break;
            case 'minus':
            case 'plus':
                basketTarget = e.target.parentElement.parentElement.parentElement;
        }
        const dishId = basketTarget ? basketTarget.getAttribute('data-dish-id') : 0;
        const data = { dishId, dishAction };

        if (dishId && basketTarget) {
            console.log('send to server:', data)
            if (dishAction !== 'remove') basketTarget.querySelector('.price-ex').classList.add('shake', 'animated');
            $.ajax({
                type: 'POST',
                url: '/basket',
                data: "data=" + JSON.stringify(data),
                success: res => {
                    console.log('received from server:', res);
                    updateBasketItem(dishAction, basketTarget, res)
                },
                error: error => {
                    console.error(error)
                }
            })
        }
    })
});

const updateBasketItem = (action, target, res) => {
    const { numOfItems, priceOfItems, totalPrice, totalDishes } = res;
    if (action === 'remove') {
        if (!numOfItems && !priceOfItems) {
            target.classList.add('opacity-0');
            let timeout = setTimeout(() => {
                target.remove();
                clearTimeout(timeout);
                timeout = null;
            }, 500);
        }
    } else {
        target.querySelectorAll('.number-of-item').forEach(item => {
            item.innerText = numOfItems;
        });
        target.querySelectorAll('.price-of-items').forEach(item => {
            item.innerText = priceOfItems;
        });
        target.querySelector('button[data-dish-action=minus').disabled = (numOfItems<=1);
        target.querySelector('.price-ex').classList.remove('shake', 'animated');
    }

    document.getElementById('totalPrice').innerText = totalPrice;
    document.getElementById('totalDishes').innerText = totalDishes;
};