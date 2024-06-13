<template>
	<v-dialog
		:model-value="true"
		max-width="1000"
		persistent
	>
		<v-card
      height="600"
    >
			<v-card-title>
				<v-container>
					<v-row>
						<v-col
							cols="11"
						>
							<h2>Cadastrar Consulta</h2>
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
							<v-autocomplete
								v-model="appointmentDoctor"
								label="Médico(a)"
                no-data-text="Nenhum médico(a) encontrado"
                hide-details
                item-title="doctorName"
                item-value="crm"
								:clearable="true"
                :items="doctors"
							/>
						</v-col>

            <v-col>
              <v-autocomplete
                v-model="appointmentSpeciality"
                label="Especialidade"
                no-data-text="Nenhuma especialidade encontrada"
                hide-details
                item-title="specialityName"
                item-value="idSpeciality"
                :clearable="true"
                :items="specialities"
              />
            </v-col>

						<v-col>
							<v-autocomplete
								v-model="appointmentPatient"
								label="Paciente"
                no-data-text="Nenhum paciente encontrado"
                hide-details
                item-title="patientName"
                item-value="idPatient"
								:clearable="true"
                :items="patients"
							/>
						</v-col>
					</v-row>

          <v-row>
            <v-col
              class="d-flex align-center"
            >
              <VueDatePicker
                v-model="appointmentDate"
                placeholder="Data"
                :enable-time-picker="false"
              />
            </v-col>

            <v-col
              class="d-flex align-center"
            >
              <VueDatePicker
                v-model="appointmentStartHour"
                placeholder="Hora início"
                time-picker
              />
            </v-col>

            <v-col
              class="d-flex align-center"
            >
              <VueDatePicker
                v-model="appointmentEndHour"
                placeholder="Hora fim"
                time-picker
              />
            </v-col>
          </v-row>

					<v-row>
            <v-col>
              <v-autocomplete
                v-model="appointmentIsPaid"
                label="Está pago?"
                hide-details
                :clearable="true"
                :items="['Sim', 'Não']"
              />
            </v-col>

						<v-col>
							<v-text-field
								v-model="appointmentPaidAmount"
								label="Valor pago"
                hide-details
                prefix="R$"
								:clearable="true"
							/>
						</v-col>

						<v-col>
							<v-autocomplete
								v-model="appointmentPaymentMethod"
								label="Método de pagamento"
                hide-details
								:clearable="true"
								:items="paymentMethods"
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
                color="red"
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
                color="#18435A"
                @click="handleCreateMovie"
              >
                Cadastrar Filme
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
	name: 'CreateAppointment',
	data() {
		return {
      appointmentDoctor: null,
      appointmentPatient: null,
      appointmentDate: null,
      appointmentStartHour: null,
      appointmentEndHour: null,
      appointmentIsPaid: null,
      appointmentPaidAmount: null,
      appointmentPaymentMethod: null,
      appointmentSpeciality: null,
      doctors: [],
      patients: [],
      specialities: [],
      paymentMethods: [
        {
          title: 'Cartão de Credito',
          value: 'CREDIT_CARD'
        },
        {
          title: 'Cartão de Débito',
          value: 'DEBIT_CARD'
        },
        {
          title: 'Dinheiro',
          value: 'MONEY'
        },
      ],
			shouldShowSnackBar: false,
			snackBarMessage: '',
		}
	},
  watch: {
    async appointmentDoctor(crm) {
      const responseExertSpecialities = await axios.get(`exert-specialities/${crm}`)

      if(responseExertSpecialities.data.length === 0) this.appointmentSpeciality = null

      this.specialities = responseExertSpecialities.data
    },
  },
	async mounted() {
    const [doctorsResponse, patientsResponse] = await Promise.all([
      axios.get('/doctors'),
      axios.get('/patients')
    ]);

    this.doctors = doctorsResponse.data;
    this.patients = patientsResponse.data;
	},
	methods: {
		closeModal() {
			this.$emit('closeModal')
		},
    formatHour(hour) {
      return `${String(hour.hours).padStart(2, '0')}:${String(hour.minutes).padStart(2, '0')}:${String(hour.seconds).padStart(2, '0')}`
    },
		async handleCreateMovie() {
      try {
        await axios.post('/appointments', {
          crm: this.appointmentDoctor,
          idPatient: this.appointmentPatient,
          idSpeciality: this.appointmentSpeciality,
          date: this.appointmentDate,
          startHour: this.formatHour(this.appointmentStartHour),
          endHour: this.formatHour(this.appointmentEndHour),
          isPaid: Boolean(this.appointmentIsPaid),
          paidAmount: parseFloat(this.appointmentPaidAmount),
          paymentMethod: this.appointmentPaymentMethod,
        })
        this.snackBarMessage = 'Consulta cadastrada com sucesso!!!'
        this.shouldShowSnackBar = true
      } catch (e) {
        this.snackBarMessage = 'Erro ao cadastrar consulta, verifique os campos!'
        this.shouldShowSnackBar = true
      }
		},
	}
}
</script>
