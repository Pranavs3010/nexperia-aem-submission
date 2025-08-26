// Wait for the document to be fully loaded
document.addEventListener("DOMContentLoaded", function () {
  const blogContainer = document.getElementById("blog-list-container");
  if (!blogContainer) return; // Exit if the component is not on the page

  const searchInput = document.getElementById("blog-search-input");
  const sortSelect = document.getElementById("blog-sort-select");
  const prevBtn = document.getElementById("blog-prev-btn");
  const nextBtn = document.getElementById("blog-next-btn");
  const pageIndicator = document.getElementById("blog-page-indicator");

  let allPosts = [];
  let filteredPosts = [];
  let currentPage = 1;
  const postsPerPage = 10;

  function fetchData() {
    blogContainer.innerHTML = '<div class="loader">Loading posts...</div>';

    const cachedData = sessionStorage.getItem("blogPosts");
    if (cachedData) {
      allPosts = JSON.parse(cachedData);
      filteredPosts = allPosts;
      renderPage();
      return;
    }

    fetch("/bin/getBlogPosts")
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => {
        allPosts = data;
        filteredPosts = allPosts;
        sessionStorage.setItem("blogPosts", JSON.stringify(data));
        renderPage();
      })
      .catch((error) => {
        blogContainer.innerHTML =
          '<p class="error">Failed to load blog posts. Please try again later.</p>';
        console.error("Fetch error:", error);
      });
  }

  // --- RENDERING ---
  function renderPage() {
    if (filteredPosts.length === 0) {
      blogContainer.innerHTML = "<p>No posts found.</p>";
      updatePaginationControls();
      return;
    }

    blogContainer.innerHTML = "";
    const startIndex = (currentPage - 1) * postsPerPage;
    const endIndex = startIndex + postsPerPage;
    const postsToShow = filteredPosts.slice(startIndex, endIndex);

    postsToShow.forEach((post) => {
      const postElement = document.createElement("article");
      postElement.className = "blog-post";
      postElement.innerHTML = `
                <h3 class="post-title">${post.title}</h3>
                <p class="post-body">${post.body}</p>
                <div class="post-meta">User ID: ${post.userId}</div>
            `;
      blogContainer.appendChild(postElement);
    });
    updatePaginationControls();
  }

  function handleSearch() {
    const searchTerm = searchInput.value.toLowerCase();
    filteredPosts = allPosts.filter((post) =>
      post.title.toLowerCase().includes(searchTerm)
    );
    currentPage = 1;
    renderPage();
  }

  function handleSort() {
    const sortBy = sortSelect.value;
    if (sortBy === "author") {
      filteredPosts.sort((a, b) => a.userId - b.userId);
    } else {
      // 'date', default
      filteredPosts.sort((a, b) => a.id - b.id);
    }
    currentPage = 1;
    renderPage();
  }

  function updatePaginationControls() {
    const totalPages = Math.ceil(filteredPosts.length / postsPerPage);
    pageIndicator.textContent = `Page ${currentPage} of ${totalPages || 1}`;
    prevBtn.disabled = currentPage === 1;
    nextBtn.disabled = currentPage >= totalPages;
  }

  searchInput.addEventListener("keyup", handleSearch);
  sortSelect.addEventListener("change", handleSort);

  prevBtn.addEventListener("click", () => {
    if (currentPage > 1) {
      currentPage--;
      renderPage();
    }
  });

  nextBtn.addEventListener("click", () => {
    const totalPages = Math.ceil(filteredPosts.length / postsPerPage);
    if (currentPage < totalPages) {
      currentPage++;
      renderPage();
    }
  });

  fetchData();
});
