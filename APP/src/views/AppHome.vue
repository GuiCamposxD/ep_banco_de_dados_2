<template>
  <v-container
    class="mt-5"
  >
    <v-row
      justify="center"
    >
      <v-col
        cols="auto"
      >
        <v-card
          color="#18435A"
          class="pa-5"
          elevation="3"
          height="500"
          width="350"
        >
          <v-row
            class="px-5"
            >
              <v-col>
                <h2
                  class="text-center"
                >
                  Cadastro
                </h2>
              </v-col>
            </v-row>

            <v-row
              v-for="option in configurationOptions"
              :key="option.title"
              class="px-5"
            >
              <v-col>
                <v-btn
                  rounded="xl"
                  @click="openModal(option.component)"
                >
                  {{ option.title }}
                </v-btn>
              </v-col>
            </v-row>
        </v-card>
      </v-col>

      <v-col
        cols="auto"
      >
        <v-card
          color="#18435A"
          class="pa-5"
          elevation="3"
          height="500"
          width="350"
        >
          <v-row>
            <v-col>
              <h2
                class="text-center"
              >
                Consultar
              </h2>
            </v-col>
          </v-row>

          <v-row
            v-for="option in consultOptions"
            :key="option.title"
            class="px-5"
          >
            <v-col>
              <v-btn
                rounded="xl"
                @click="openModal(option.component)"
              >
                {{ option.title }}
              </v-btn>
            </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
  </v-container>

  <component
    :is="currentModal"
    v-if="currentModal"
    @closeModal="closeModal"
  />
</template>

<script>
import SearchDoctor from '@/components/Modals/SearchDoctor.vue'
import BestActorAward from '@/components/Modals/BestActorAwards.vue'
import CreateSchedule from '@/components/Modals/CreateSchedule.vue'
import CreateSpeciality from '@/components/Modals/CreateSpeciality.vue'
import CreatePatient from '@/components/Modals/CreatePatient.vue'
import CreateAppointment from '@/components/Modals/CreateAppointment.vue'
import CreateDisease from '@/components/Modals/CreateDisease.vue'
import CreateDoctor from '@/components/Modals/CreateDoctor.vue'
import CreateDiagnose from '@/components/Modals/CreateDiagnose.vue'
import MostAwardedMovie from '@/components/Modals/MostAwardedMovie.vue'
import MostAwardedPerson from '@/components/Modals/MostAwardedPerson.vue'
import MostGrossingMovie from '@/components/Modals/MostGrossingMovie.vue'

export default {
  name: 'AppHome',
  components: {
    SearchDoctor,
    BestActorAward,
    CreateSchedule,
    CreateSpeciality,
    CreatePatient,
    CreateAppointment,
    CreateDisease,
    CreateDoctor,
    CreateDiagnose,
    MostAwardedMovie,
    MostAwardedPerson,
    MostGrossingMovie,
},
  data() {
    return {
      currentModal: null,
      configurationOptions: [
        {
          title: 'Inserir Médico',
          component: 'CreateDoctor',
        },
        {
          title: 'Inserir Agenda',
          component: 'CreateSchedule',
        },
        {
          title: 'Inserir Especialidade',
          component: 'CreateSpeciality',
        },
        {
          title: 'Inserir Paciente',
          component: 'CreatePatient',
        },
        {
          title: 'Inserir Consulta',
          component: 'CreateAppointment',
        },
        {
          title: 'Inserir Doença',
          component: 'CreateDisease',
        },
        {
          title: 'Inserir Diagnostico',
          component: 'CreateDiagnose',
        },
      ],
      consultOptions: [
        {
          title: 'Médicos',
          component: 'SearchDoctor',
        },
        {
          title: 'Agendas',
          component: this.openMostAwardedMovieModal,
        },
        {
          title: 'Especialidade',
          component: this.openMostGrossingMovieModal,
        },
        {
          title: 'Paciente',
          component: this.openBestActorsAwardsModal,
        },
        {
          title: 'Consulta',
          component: this.openAwardsModal,
        },
        {
          title: 'Doença',
          component: this.openDiseaseModal,
        },
        {
          title: 'Diagnóstico',
          component: this.openDiagnoseModal,
        },
      ]
    }
  },
  methods: {
    openModal(modalName) {
      this.currentModal = modalName
    },
    closeModal() {
      this.currentModal = null
    },
  },
}
</script>
