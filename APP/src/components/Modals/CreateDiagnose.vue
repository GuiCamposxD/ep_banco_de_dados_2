<template>
	<v-dialog
		max-width="1250"
		:model-value="true"
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
							Cadastrar Diagnóstico
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
				</v-container>
			</v-card-title>

			<v-card-text>
				<v-container>
					<v-row>
            <v-col>
							<v-text-field
								v-model="diagnoseRecommendedTreatment"
								label="Tratamento Recomendado"
                :clearable="true"
							/>
						</v-col>

            <v-col>
              <v-text-field
                v-model="diagnosePrescriptionMedicines"
								label="Remédios Receitados"
								:clearable="true"
              />
            </v-col>

            <v-col>
              <v-text-field
                v-model="diagnoseObservations"
                label="Observações"
                :clearable="true"
              />
            </v-col>
					</v-row>

          <v-row>
						<v-col>
							<v-autocomplete
								v-model="diagnoseAppointment"
								label="Consulta"
                :item-title="getAppointmentItem"
                item-value="idAppointment"
								:clearable="true"
								:items="appointments"
							/>
						</v-col>

            <v-col>
              <v-autocomplete
                v-model="diagnoseDisease"
                label="Doença"
                item-title="diseaseName"
                item-value="idDisease"
                :clearable="true"
                :items="diseases"
              />
            </v-col>
          </v-row>

					<v-row
            justify="end"
          >
            <v-col
              cols="2"
            >
              <v-btn
                variant="flat"
                color="#FA7654"
                @click="closeModal"
              >
                Cancelar
              </v-btn>
            </v-col>

            <v-col
              cols="auto"
            >
              <v-btn
                variant="flat"
                color="#FAC95F"
                @click="handleCreatePersonAward"
              >
                Cadastrar Premiação para Pessoa
              </v-btn>
            </v-col>
          </v-row>
				</v-container>
			</v-card-text>
		</v-card>

		<v-snackbar
			v-model="shouldShowSnackBar"
			color="#FAC95F"
			elevation="24"
			:timeout="2000"
			location="center"
		>
			<p>{{ snackBarMessage }}</p>

			<template v-slot:actions>
				<v-btn
					color="pink"
					variant="text"
					@click="closeSnackbar"
				>
					Close
				</v-btn>
			</template>
		</v-snackbar>
	</v-dialog>
</template>

<script>
import axios from 'axios'

export default {
	name: 'CreateDiagnose',
	data() {
		return {
      appointments: [],
      diseases: [],
      diagnoseDisease: null,
      diagnoseAppointment: null,
      diagnosePrescriptionMedicines: null,
      diagnoseRecommendedTreatment: null,
      diagnoseObservations: null,
			shouldShowSnackBar: false,
			snackBarMessage: '',
		}
	},
	async mounted() {
		const [appointmentsResponse, diseaseResponse] = await Promise.all([
      axios.get('/appointments'),
      axios.get('/diseases'),
    ])

    this.appointments = appointmentsResponse.data
    this.diseases = diseaseResponse.data
	},
	methods: {
		closeModal() {
			this.$emit('closeModal')
		},
    getAppointmentItem(item) {
      return `${item.startHour} - ${item.endHour} - ${item.date} - ${item.doctor.doctorName}`
    },
		async handleCreatePersonAward() {
			const response = await axios.post('/pessoa_premiacao', {
        recommendedTreatment: this.diagnoseAppointment,
        prescriptionMedicines: this.diagnosePrescriptionMedicines,
        observations: this.diagnoseObservations,

			})

			if (response.status === 200) {
				this.snackBarMessage = 'Premiação para pessoa cadastrada com sucesso!!!'
				this.shouldShowSnackBar = true
				return
			}

			if (response.status === 500) {
				this.snackBarMessage = 'Erro ao cadastrar premiação para pessoa, verifique os campos'
				this.shouldShowSnackBar = true
			}
		},
	}
}
</script>
