<template>
	<v-dialog
		:model-value="true"
		max-width="800"
    :persistent="true"
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
							<h2>
							Cadastrar Agenda
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
							<v-autocomplete
								v-model="weekDay"
								label="Dias da Semana"
                hide-details
                :items="weekDays"
								:clearable="true"
							/>
						</v-col>

						<v-col
              class="d-flex align-center"
            >
							<VueDatePicker
								v-model="startHour"
                placeholder="Hora Início"
                time-picker
							/>
						</v-col>
					</v-row>

          <v-row>
            <v-col
              class="d-flex align-center"
            >
              <VueDatePicker
                v-model="endHour"
                placeholder="Hora Fim"
                time-picker
              />
            </v-col>

            <v-col>
              <v-autocomplete
                v-model="doctorCrm"
                label="Doutor"
                item-title="doctorName"
                item-value="crm"
                hide-details
                :clearable="true"
                :items="doctors"
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
                @click="handleCreateAward"
              >
                Cadastrar Agenda
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
	name: 'CreateSchedule',
	emits: [
    'closeModal',
  ],
	data() {
		return {
			doctorCrm: null,
			doctors: [],
			startHour: null,
      weekDay: null,
			shouldShowSnackBar: false,
			snackBarMessage: '',
			endHour: null,
      weekDays: [
        {
          title: 'Segunda-Feira',
          value: 'MONDAY',
        },
        {
          title: 'Terça-Feira',
          value: 'TUESDAY',
        },
        {
          title: 'Quarta-Feira',
          value: 'WEDNESDAY',
        },
        {
          title: 'Quinta-feira',
          value: 'THURSDAY',
        },
        {
          title: 'Sexta-Feira',
          value: 'FRIDAY',
        },
        {
          title: 'Sábado',
          value: 'SATURDAY',
        },
        {
          title: 'Domingo',
          value: 'SUNDAY',
        },
      ],
    }
	},
	async mounted() {
		const response = await axios.get('/doctors')
		this.doctors = response.data
	},
	methods: {
		closeModal() {
			this.$emit('closeModal')
		},
    closeSnackbar() {
      this.shouldShowSnackBar = false
    },
    formatHour(hour) {
      return String(hour).padStart(2, '0')
    },
		async handleCreateAward() {
			try {
        await axios.post('/schedules', {
          crm: this.doctorCrm,
          weekDay: this.weekDay,
          startHour: `${this.formatHour(this.startHour.hours)}:${this.formatHour(this.startHour.minutes)}:${this.formatHour(this.startHour.seconds)}`,
          endHour: `${this.formatHour(this.endHour.hours)}:${this.formatHour(this.endHour.minutes)}:${this.formatHour(this.endHour.seconds)}`,
        })

        this.snackBarMessage = 'Agenda cadastrada com sucesso!!!'
        this.shouldShowSnackBar = true
      } catch (e) {
        this.snackBarMessage = 'Erro ao cadsatrar Agenda, verifique os campos'
        this.shouldShowSnackBar = true
      }
		},
	}
}
</script>
