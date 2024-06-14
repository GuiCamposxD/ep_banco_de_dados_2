<template>
	<v-dialog
		:model-value="true"
		max-width="600"
		:persistent="true"
	>
		<v-card>
			<v-card-title>
				<v-container>
					<v-row>
						<v-col
							cols="11"
						>
							<h2>
                Médicos
              </h2>
						</v-col>

						<v-col
							cols="1"
							justify="end"
						>
							<v-btn
								variant="text"
								density="comfortable"
								icon="mdi-close"
								@click="closeModal"
							/>
						</v-col>
					</v-row>

          <v-row
            justify="center"
          >
            <v-col
              cols="6"
            >
              <v-autocomplete
								v-model="selectedDoctor"
								label="Médicos"
								:clearable="true"
								item-title="doctorName"
								item-value="crm"
								return-object
                hide-details
								:items="doctors"
							/>
            </v-col>
          </v-row>
				</v-container>
			</v-card-title>

			<v-card-text>
        <v-card
          v-for="doctor in doctors"
          class="mb-5"
          :key="doctor.crm"
        >
          <v-container>
            <v-row>
              <v-col
                cols="10"
              >
                <p><b>Crm:</b> {{ doctor.crm }}</p>
                <p><b>Nome:</b> {{ doctor.doctorName }}</p>
                <p><b>Telefone:</b> {{ doctor.doctorPhone }}</p>
                <p><b>Porcentagem:</b> {{ doctor.percentage }}</p>
              </v-col>

              <v-col
                class="d-flex align-center"
              >
                <v-tooltip
                  text="Editar Doutor"
                  location="top"
                >
                  <template v-slot:activator="{ props }">
                    <v-btn
                      variant="flat"
                      color="#18435A"
                      icon="mdi-pencil"
                      v-bind="props"
                    />
                  </template>
                </v-tooltip>
              </v-col>
            </v-row>
          </v-container>
        </v-card>
			</v-card-text>
		</v-card>
	</v-dialog>
</template>

<script>
import axios from 'axios'

export default {
  name: 'SearchDoctor',
  data()  {
    return {
      selectedDoctor: null,
      doctors: [],
      doctorsTableheaders: [
        { title: 'Crm', key: 'crm' },
        { title: 'Nome', key: 'doctorName' },
        { title: 'Telefone', key: 'doctorPhone' },
        { title: 'Porcentagem', key: 'percentage'},
      ],
    }
  },
  async mounted() {
    const doctorsReponse = await axios.get('/doctors')
		this.doctors = doctorsReponse.data
  },
  // watch: {
  //   selectedAward: {
  //     async handler(award) {
  //       if (!award) {
  //         this.actors = []
  //         this.movies = []
  //         return
  //       }
  //
  //       const reponseActors = await axios.get('/pessoa_indicada_premio', {
  //         params: {
  //           edicao_ano: award.edicao_ano,
  //           edicao_nome_evento: award.edicao_nome_evento,
  //           tipo: award.tipo,
  //         },
  //       })
  //       this.actors = reponseActors.data
  //
  //       const reponseMovies = await axios.get('/filme_indicado_premio', {
  //         params: {
  //           edicao_ano: award.edicao_ano,
  //           edicao_nome_evento: award.edicao_nome_evento,
  //           tipo: award.tipo,
  //         },
  //       })
  //       this.movies = reponseMovies.data
  //     },
  //   },
  // },
  methods: {
    closeModal() {
			this.$emit('closeModal')
    },
  },
}
</script>
