<template>
  <v-container
    class="mt-5"
  >
    <v-row
      justify="space-around"
    >
      <v-col
        cols="auto"
        class="configuration__container"
      >
        <v-row
          class="px-5"
        >
          <v-col
            cols="auto"
          >
            <h2>Configurações</h2>
          </v-col>
        </v-row>

        <v-row
          v-for="option in configurationOptions"
          :key="option.title"
          class="px-5"
        >
          <v-col>
            <v-btn
              variant="outlined"
              @click="handleConfigurationOpenModalCall(option.title)"
            >
              {{ option.title }}
            </v-btn>
          </v-col>
        </v-row>
      </v-col>

      <v-col
        cols="auto"
        class="report__container"
      >
        <v-row
          class="px-5"
        >
          <v-col
            cols="auto"
          >
            <h2>Relatórios</h2>
          </v-col>
        </v-row>

        <v-row
          v-for="option in reportOptions"
          :key="option.title"
          class="px-5"
        >
          <v-col>
            <v-btn
              variant="outlined"
              @click="handleReportOpenModalCall(option.title)"
            >
              {{ option.title }}
            </v-btn>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>

  <CreatePerson
    v-if="shouldOpenCreatePersonModal"
    @closeModal="closeCreatePersonModal"
  />

  <CreateEvent
    v-if="shouldOpenCreateEventModal"
    @closeModal="closeCreateEventModal"
  />

  <CreateAward
    v-if="shouldOpenCreateAwardModal"
    @closeModal="closeCreateAwardModal"
  />

  <CreateMovie
    v-if="shouldOpenCreateMovieModal"
    @closeModal="closeCreateMovieModal"
  />

  <CreateEdition
    v-if="shouldOpenCreateEditionModal"
    @closeModal="closeCreateEditionModal"
  />

  <CreateMovieAward
    v-if="shouldOpenCreateMovieAward"
    @closeModal="closeCreateMovieAward"
  />

  <CreatePersonAward
    v-if="shouldOpenCreatePersonAwardModal"
    @closeModal="closeCreatePersonAwardModal"
  />

  <MostAwardedPerson
    v-if="shouldOpenMostAwardedPersonModal"
    @closeModal="closeMostAwardedPersonModal"
  />

  <MostAwardedMovie
    v-if="shouldOpenMostAwardedMovieModal"
    @closeModal="closeMostAwardedMovieModal"
  />

  <MostGrossingMovie
    v-if="shouldOpenMostGrossingMovieModal"
    @closeModal="closeMostGrossingMovieModal"
  />

  <BestActorAward
    v-if="shouldOpenBestActorsAwardsModal"
    @closeModal="closeBestActorsAwardsModal"
  />

  <Awards
    v-if="shouldOpenAwardsModal"
    @closeModal="closeAwardsModal"
  />
</template>

<script>
import Awards from '@/components/Modals/Awards.vue'
import BestActorAward from '@/components/Modals/BestActorAwards.vue'
import CreateAward from '@/components/Modals/CreateAward.vue'
import CreateEdition from '@/components/Modals/CreateEdition.vue'
import CreateEvent from '@/components/Modals/CreateEvent.vue'
import CreateMovie from '@/components/Modals/CreateMovie.vue'
import CreateMovieAward from '@/components/Modals/CreateMovieAward.vue'
import CreatePerson from '@/components/Modals/CreatePerson.vue'
import CreatePersonAward from '@/components/Modals/CreatePersonAward.vue'
import MostAwardedMovie from '@/components/Modals/MostAwardedMovie.vue'
import MostAwardedPerson from '@/components/Modals/MostAwardedPerson.vue'
import MostGrossingMovie from '@/components/Modals/MostGrossingMovie.vue'

export default {
  name: 'AppHome',
  components: {
    Awards,
    BestActorAward,
    CreateAward,
    CreateEdition,
    CreateEvent,
    CreateMovie,
    CreateMovieAward,
    CreatePerson,
    CreatePersonAward,
    MostAwardedMovie,
    MostAwardedPerson,
    MostGrossingMovie,
},
  data() {
    return {
      shouldOpenCreateAwardModal: false,
      shouldOpenCreateEditionModal: false,
      shouldOpenCreateEventModal: false,
      shouldOpenCreateMovieAward: false,
      shouldOpenCreateMovieModal: false,
      shouldOpenCreatePersonAwardModal: false,
      shouldOpenCreatePersonModal: false,
      shouldOpenMostAwardedMovieModal: false,
      shouldOpenMostAwardedPersonModal: false,
      shouldOpenMostGrossingMovieModal: false,
      shouldOpenBestActorsAwardsModal: false,
      shouldOpenAwardsModal: false,
      configurationOptions: [
        { title: 'Inserir Nova Pessoa' },
        { title: 'Inserir Novo Evento' },
        { title: 'Inserir Nova Edição' },
        { title: 'Inserir Novo Prêmio' },
        { title: 'Inserir Novo Filme' },
        { title: 'Inserir Premiação para Filme' },
        { title: 'Inserir Premiação para Ator (Atriz)' },
      ],
      reportOptions: [
        { title: 'As 10 Pessoas Mais Premiadas' },
        { title: 'Os 10 Filmes Mais Premiados' },
        { title: 'Os 10 Filmes com Maior Bilheteria' },
        { title: 'Melhores Atores (Atrizes)' },
        { title: 'Indicados a Prêmios' },
      ]
    }
  },
  methods: {
    handleConfigurationOpenModalCall(title) {
      if (title === 'Inserir Nova Pessoa') this.openCreatePersonModal()

      if (title === 'Inserir Novo Evento') this.openCreateEventModal()

      if (title === 'Inserir Novo Prêmio') this.openCreateAwardModal()

      if (title === 'Inserir Nova Edição') this.openCreateEditionModal()
      
      if (title === 'Inserir Novo Filme') this.openCreateMovieModal()

      if (title === 'Inserir Premiação para Filme') this.openCreateMovieAward()

      if (title === 'Inserir Premiação para Ator (Atriz)') this.openCreatePersonAward()
    },
    handleReportOpenModalCall(title) {
      if (title === 'As 10 Pessoas Mais Premiadas') this.openMostAwardedPersonModal()

      if (title === 'Os 10 Filmes Mais Premiados') this.openMostAwardedMovieModal()
      
      if (title === 'Os 10 Filmes com Maior Bilheteria') this.openMostGrossingMovieModal()

      if (title === 'Melhores Atores (Atrizes)') this.openBestActorsAwardsModal()

      if (title === 'Indicados a Prêmios') this.openAwardsModal()

    },
    openCreatePersonModal() {
      this.shouldOpenCreatePersonModal = true
    },
    closeCreatePersonModal() {
      this.shouldOpenCreatePersonModal = false
    },
    openCreateEventModal() {
      this.shouldOpenCreateEventModal = true
    },
    closeCreateEventModal() {
      this.shouldOpenCreateEventModal = false
    },
    openCreateAwardModal() {
      this.shouldOpenCreateAwardModal = true 
    },
    closeCreateAwardModal() {
      this.shouldOpenCreateAwardModal = false 
    },
    openCreateMovieModal() {
      this.shouldOpenCreateMovieModal = true 
    },
    closeCreateMovieModal() {
      this.shouldOpenCreateMovieModal = false 
    },
    openCreateEditionModal(){
      this.shouldOpenCreateEditionModal = true
    },
    closeCreateEditionModal() {
      this.shouldOpenCreateEditionModal = false
    },
    openCreateMovieAward() {
      this.shouldOpenCreateMovieAward = true
    },
    closeCreateMovieAward() {
      this.shouldOpenCreateMovieAward = false
    },
    openCreatePersonAward() {
      this.shouldOpenCreatePersonAwardModal = true
    },
    closeCreatePersonAwardModal() {
      this.shouldOpenCreatePersonAwardModal = false
    },
    openMostAwardedPersonModal() {
      this.shouldOpenMostAwardedPersonModal = true
    },
    closeMostAwardedPersonModal() {
      this.shouldOpenMostAwardedPersonModal = false
    },
    openMostAwardedMovieModal() {
      this.shouldOpenMostAwardedMovieModal = true
    },
    closeMostAwardedMovieModal() {
      this.shouldOpenMostAwardedMovieModal = false
    },
    openMostGrossingMovieModal() {
      this.shouldOpenMostGrossingMovieModal = true
    },
    closeMostGrossingMovieModal() {
      this.shouldOpenMostGrossingMovieModal = false
    },
    openBestActorsAwardsModal() {
      this.shouldOpenBestActorsAwardsModal = true
    },
    closeBestActorsAwardsModal() {
      this.shouldOpenBestActorsAwardsModal = false
    },
    openAwardsModal() {
      this.shouldOpenAwardsModal = true
    },
    closeAwardsModal() {
      this.shouldOpenAwardsModal = false
    },
  },
}
</script>

<style>
  .configuration__container, .report__container {
    border: 1px solid;
    border-radius: 1rem
  };
</style>
