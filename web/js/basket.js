const basketItems = document.querySelectorAll('.basket-item button');
basketItems.forEach(item => {
    item.addEventListener('click', e => {
        let basketTarget;
        const action = e.target.getAttribute('data-action');
        switch (action) {
            case 'remove':
                if (confirm('Do you want to delete this item?')) {
                    basketTarget = e.target.parentElement;
                }
                break;
            case 'minus':
            case 'plus':
                basketTarget = e.target.parentElement.parentElement.parentElement;
        }
        const id = basketTarget ? basketTarget.getAttribute('data-id') : 0;
        const type = basketTarget ? basketTarget.getAttribute('data-type') : null;
        const data = { id, type, action };


        if (id && basketTarget) {
            console.log('send to server:', data);
            if (action !== 'remove') basketTarget.querySelector('.price-ex').classList.add('shake', 'animated');
            $.ajax({
                type: 'POST',
                url: '/basket',
                data: "data=" + JSON.stringify(data),
                success: res => {
                    console.log('received from server:', res);
                    try {
                        // res = JSON.parse(res);
                        updateBasketItem(action, basketTarget, res)
                    } catch (err) {
                        console.error(err);
                    }
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
            // document.querySelector('#no-dishes').classList.remove('hide');
        }
        if (totalDishes === 0) {
            document.querySelector('.total-price').classList.add('hide');
        }
    } else {
        target.querySelectorAll('.number-of-item').forEach(item => {
            item.innerText = numOfItems;
        });
        target.querySelectorAll('.price-of-items').forEach(item => {
            item.innerText = priceOfItems;
        });
        target.querySelector('button[data-action=minus').disabled = (numOfItems<=1);
        target.querySelector('.price-ex').classList.remove('shake', 'animated');
    }

    document.getElementById('totalPrice').innerText = totalPrice;
    document.getElementById('totalDishes').innerText = totalDishes;
};