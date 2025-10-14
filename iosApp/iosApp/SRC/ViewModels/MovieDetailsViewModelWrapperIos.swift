import ComposeApp
import Combine

class MovieDetailsViewModelWrapperIos: ObservableObject {
    let vm: MovieDetailsVM

    @Published var movie: MovieDetailDto? = nil
    @Published var isLoading: Bool = false
    @Published var errorMessage: String? = nil

    init(vm: MovieDetailsVM) {
        self.vm = vm

        vm.movieState.addObserver { [weak self] movieKotlin in
            guard let self = self else { return }

            DispatchQueue.main.async {
                self.movie = movieKotlin as? MovieDetailDto
            }
        }

        vm.isLoading.addObserver { [weak self] loading in
            guard let self = self else { return }

            DispatchQueue.main.async {
                if let boolValue = loading as? KotlinBoolean {
                    self.isLoading = boolValue.boolValue
                } else if let boolValue = loading as? Bool {
                    self.isLoading = boolValue
                } else {
                    self.isLoading = false
                }
            }
        }

        vm.error.addObserver { [weak self] error in
            guard let self = self else { return }

            DispatchQueue.main.async {
                self.errorMessage = error as? String
            }
        }
    }

    func loadMovie(id: String) {
        vm.loadMovie(id: id)
    }

    deinit {
        vm.movieState.removeAllObservers()
        vm.isLoading.removeAllObservers()
        vm.error.removeAllObservers()
    }
}
