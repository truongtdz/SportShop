// Product Data for all sections
const newProducts = [
  {
    name: "Morelia Neo IV Pro AS",
    price: "3.099.000₫",
    image: "anh_nen_2.jpeg",
    colors: ["#ffffff", "#000000"],
  },
  {
    name: "Morelia Neo II MD",
    price: "2.599.000₫",
    image: "anh_nen_2.jpeg",
    colors: ["#0000FF", "#FF0000"],
  },
  {
    name: "Morelia Sala Classic IN",
    price: "1.899.000₫",
    image: "anh_nen_2.jpeg",
    colors: ["#008000", "#FFFF00"],
  },
  {
    name: "Monarcida Neo II Select",
    price: "1.499.000₫",
    image: "anh_nen_2.jpeg",
    colors: ["#FFFFFF", "#000000"],
  },
  {
    name: "Rebula V3 AS",
    price: "2.199.000₫",
    image: "anh_nen_2.jpeg",
    colors: ["#FFA500", "#800080"],
  },
];

const bestsellerProducts = [
  {
    name: "Morelia Neo IV Pro AS",
    price: "3.099.000₫",
    image: "anh_nen_2.jpeg",
    colors: ["#ffffff", "#000000"],
  },
  {
    name: "Morelia Neo II MD",
    price: "2.599.000₫",
    image: "anh_nen_2.jpeg",
    colors: ["#0000FF", "#FF0000"],
  },
  {
    name: "Morelia Sala Classic IN",
    price: "1.899.000₫",
    image: "anh_nen_2.jpeg",
    colors: ["#008000", "#FFFF00"],
  },
];

const hotProducts = [
  {
    name: "Monarcida Neo II Select",
    price: "1.499.000₫",
    image: "anh_nen_2.jpeg",
    colors: ["#FFFFFF", "#000000"],
  },
  {
    name: "Rebula V3 AS",
    price: "2.199.000₫",
    image: "anh_nen_2.jpeg",
    colors: ["#FFA500", "#800080"],
  },
  {
    name: "Monarcida Neo II MD",
    price: "2.099.000₫",
    image: "anh_nen_2.jpeg",
    colors: ["#000000", "#FF0000"],
  },
];

let currentIndex = 0;
const productsPerPage = 3; // Set to 3 for display

function displayProducts(products, containerId, currentIndex) {
  const productList = document.getElementById(containerId);
  productList.style.opacity = 0;
  setTimeout(() => {
    productList.innerHTML = ""; // Clear previous content
    for (
      let i = currentIndex;
      i < Math.min(currentIndex + productsPerPage, products.length);
      i++
    ) {
      const product = products[i];
      const productDiv = document.createElement("div");
      productDiv.classList.add("product");
      productDiv.innerHTML = `
        <a href="#">
          <img src="${product.image}" alt="${product.name}" />
        </a>
        <h3>${product.name}</h3>
        <p>Giá: ${product.price}</p>
        <div class="colors">
          ${product.colors
            .map(
              (color) =>
                `<span class="color" style="background-color:${color}"></span>`
            )
            .join("")}
        </div>
        <div class="actions">
          <button><i class="fas fa-shopping-cart"></i>Mua ngay</button>
          <button><i class="fas fa-eye"></i>Xem</button>
        </div>`;
      productList.appendChild(productDiv);
    }
    productList.style.opacity = 1;
  }, 300); // Delay to apply the fade effect
}

function nextProduct(products, containerId, currentIndex, updateIndex) {
  if (currentIndex < products.length - productsPerPage) {
    updateIndex(currentIndex + 1);
    displayProducts(products, containerId, currentIndex + 1);
  }
}

function prevProduct(products, containerId, currentIndex, updateIndex) {
  if (currentIndex > 0) {
    updateIndex(currentIndex - 1);
    displayProducts(products, containerId, currentIndex - 1);
  }
}

// Handlers for new products
document.getElementById("nextBtn").onclick = function () {
  nextProduct(
    newProducts,
    "productList",
    currentIndex,
    (newIndex) => (currentIndex = newIndex)
  );
};
document.getElementById("prevBtn").onclick = function () {
  prevProduct(
    newProducts,
    "productList",
    currentIndex,
    (newIndex) => (currentIndex = newIndex)
  );
};

// Initial product display on page load
window.onload = function () {
  displayProducts(newProducts, "productList", currentIndex);
  displayProducts(bestsellerProducts, "bestsellerProductList", 0);
  displayProducts(hotProducts, "hotProductList", 0);
};
