// Wait until the DOM content tree loads entirely before attaching listeners
document.addEventListener('DOMContentLoaded', function () {
    
    const themeToggleBtn = document.getElementById('themeToggleBtn');
    const liveSearchInput = document.getElementById('liveSearchInput');

    // --- SECTION 1: THEME CONFIGURATION CONTROL ENGINE ---
    function applyTheme(theme) {
        if (theme === 'dark') {
            document.documentElement.setAttribute('data-theme', 'dark');
            if(themeToggleBtn) themeToggleBtn.innerText = "☀️ Toggle Light Mode";
        } else {
            document.documentElement.removeAttribute('data-theme');
            if(themeToggleBtn) themeToggleBtn.innerText = "🌙 Toggle Dark Mode";
        }
    }

    // Initialize checking values from storage preferences on launch
    const savedTheme = localStorage.getItem('theme') || 'light';
    applyTheme(savedTheme);

    // Global accessibility handler to trigger toggle changes safely from the inline HTML template
    window.toggleTheme = function() {
        const currentTheme = document.documentElement.getAttribute('data-theme') === 'dark' ? 'dark' : 'light';
        const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
        
        localStorage.setItem('theme', newTheme);
        applyTheme(newTheme);
    };

    // --- SECTION 2: LIVE INVENTORY FILTERING LOGIC ---
    if (liveSearchInput) {
        liveSearchInput.addEventListener('keyup', function () {
            const filter = liveSearchInput.value.toUpperCase();
            const table = document.getElementById("inventoryTable");
            const tr = table.getElementsByTagName("tr");

            // Loop rows selectively skipping table headers (index 0)
            for (let i = 1; i < tr.length; i++) {
                let nameCell = tr[i].getElementsByClassName("asset-name")[0];
                let typeCell = tr[i].getElementsByClassName("asset-type")[0];
                
                if (nameCell || typeCell) {
                    let nameText = nameCell ? nameCell.textContent || nameCell.innerText : "";
                    let typeText = typeCell ? typeCell.textContent || typeCell.innerText : "";
                    
                    // Match query criteria against tokens inside product columns
                    if (nameText.toUpperCase().indexOf(filter) > -1 || typeText.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        });
    }
});
