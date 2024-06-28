<template>
    <div class="grid">
        <div class="col-12">
            <div class="card docs">
                <h3>Current Version</h3>
                <p>Nuxt 3 and PrimeVue 3</p>

                <h5>Installation</h5>
                <p>
                    Sakai is an application template for Vue based on the <a href="https://nuxtjs.org" class="font-medium text-primary hover:underline">Nuxt 3</a>. To get started, clone the <a href="https://github.com/primefaces/sakai-nuxt" class="font-medium text-primary hover:underline">repository</a> from GitHub and install the dependencies with npm or yarn.
                </p>
                <CodeHighlight>npm install</CodeHighlight>

                <h6>Development</h6>
                <p>Start the development server on <span class="font-medium bg-primary-100 text-primary-900 border-round text-sm px-2 py-1">http://localhost:3000/</span>.</p>
                <CodeHighlight>npm run dev</CodeHighlight>

                <h6>Production</h6>
                <p>Build the application for production.</p>
                <CodeHighlight>npm run build</CodeHighlight>

                <h6>Preview</h6>
                <p>Locally preview production build.</p>
                <CodeHighlight>npm run preview</CodeHighlight>

                <p>Check out the <a class="font-medium text-primary hover:underline" href="https://nuxt.com/docs/getting-started/deployment">deployment documentation</a> for more information.</p>

                <h5>Structure</h5>
                <p>Sakai consists of a couple folders, demos and layout have been separated so that you can easily use the necessary parts when integrating with an existing Nuxt app.</p>
                <ul class="line-height-3">
                    <li><span class="text-primary font-medium">layouts</span>: Main layout files, required</li>
                    <li><span class="text-primary font-medium">pages</span>: Demo pages</li>
                    <li><span class="text-primary font-medium">public/demo</span>: Assets used in demos</li>
                    <li><span class="text-primary font-medium">public/layout</span>: Assets used in layout, required</li>
                    <li><span class="text-primary font-medium">assets/demo</span>: Styles used in demos</li>
                    <li><span class="text-primary font-medium">assets/layout</span>: SCSS files of the main layout, required</li>
                </ul>

                <h5>Menu</h5>
                <p>Main menu is defined at <span class="text-primary font-medium">layout/AppMenu.vue</span> file.</p>

                <h5>PrimeVue Theme</h5>
                <p>Sakai theming is based on the PrimeVue theme being used. Default theme is <b>aura-light-green</b>.</p>

                <h5>SASS Variables</h5>
                <p>In case you would like to customize the main layout variables, open <b>_variables.scss</b> file under layout folder. Saving the changes will be reflected instantly at your browser.</p>

                <h6>assets/layout/styles/layout/_variables.scss</h6>
                <CodeHighlight>
                    /* General */
                    <br />
                    $scale:14px; /* main font size */
                    <br />
                    $borderRadius:12px; /* border radius of layout element e.g. card, sidebar */
                    <br />
                    $transitionDuration:.2s; /* transition duration of layout elements e.g. sidebar, overlay menus */
                </CodeHighlight>
            </div>
        </div>
    </div>
</template>
