<script>
  export let title;
  export let text;
  export let goTo;
</script>

<a href={goTo} class="box">
  <slot />
  <h2 style="margin-top: .5em; margin-bottom: .1em;">{title}</h2>
  <p style="margin: 0; color: var(--subtitle-color)">{text}</p>
</a>

<style>
  .box {
    background-color: var(--box-bg);
    padding: 1.5rem 1.2rem;
    border-radius: 0.5rem;
  }
</style>
