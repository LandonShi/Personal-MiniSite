const floatOn = options => {
    var el = options.el,
        x = options.x,
        xIsPos = options.xIsPos || Math.floor(Math.random()),
        updateX = options.updateX || Math.floor(Math.random()),
        curTop = parseInt(el.style.top),
        curLeft = parseInt(el.style.left);

    if (curTop > -50) {
        el.style.top = `${--curTop}px`;
    } else {
        el.style.top = `${innerHeight + 50}px`;
    }

    if (updateX) {
        if (xIsPos) {
            if (curLeft > x + 10) {
                xIsPos = false;
            } else {
                el.style.left = `${curLeft + 1}px`;
            }
        } else {
            if (curLeft < x - 10) {
                xIsPos = true;
            } else {
                el.style.left = `${--curLeft}px`;
            }
        }
    }

    updateX = updateX ? false : true;

    requestAnimationFrame(
        floatOn.bind(null, { el: el, x: x, xIsPos: xIsPos, updateX: updateX })
    );
};

class Bubble {
    constructor(target, i) {
        this.bubble = document.createElement("div");
        this.bubble.classList.add("bubble");
        this.x = Math.floor(Math.random() * innerWidth);
        this.y = Math.floor(Math.random() * innerHeight);
        this.scale = Math.random();
        this.pos = Math.round(Math.random());

        this.bubble.style.top = `${this.y}px`;
        this.bubble.style.left = `${this.x}px`;
        this.bubble.style.transform = `translateZ(${
            this.pos ? "" : "-"
        }${this.scale.toFixed(2) * 1000}px)`;

        setTimeout(() => {
            target.appendChild(this.bubble);
    }, i * 50);
        setTimeout(floatOn.bind(null, { el: this.bubble, x: this.x }), i * 50);
    }
}

for (let i = 0; i < 100; i++) {
    new Bubble(document.querySelector(".soda"), i);
}